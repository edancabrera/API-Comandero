param (
    [string]$InstallationPath = "C:\tomcat",
    [string]$ServiceName = "Tomcat10",

    [string]$MysqlService,
    [string]$JavaHome,

    # BD 
    [string]$DbUser,
    [string]$DbPass
)

function Show-CloseCountdown {
    param (
        [string]$Message = "Cerrando instalador...",
        [int]$ExitCode = 0,
        [string]$TxtColor = "White",
        [string]$BgColor = "Black"
        )
        
    Write-Host ""
    Write-Host $Message -ForegroundColor $TxtColor -BackgroundColor $BgColor
    Write-Host ""

    for ($i = 10; $i -ge 1; $i--) {
        Write-Host "Cerrando consola en $i segundos..." -NoNewline
        Start-Sleep -Seconds 1
        Write-Host "`r" -NoNewline
    }
    Stop-Transcript -ErrorAction SilentlyContinue
    exit $ExitCode
}

Set-Location -Path $PSScriptRoot

#Verificar si existe el directorio de instalación, si no, crear
if (!(Test-Path $InstallationPath)) {
    New-Item -ItemType Directory -Force -Path $InstallationPath
}

$logPath = "$InstallationPath\installation.log"
Start-Transcript -Path $logPath -Append

Write-Host "=== INICIANDO INSTALACION: COMANDERO API ==="

# ====================
# VALIDACIONES
# ====================
Write-Host "Validando Java..."

Write-Host "Solicitando la ruta del jdk.."
if ([string]::IsNullOrWhiteSpace($JavaHome)) {
    Add-Type -AssemblyName System.Windows.Forms

    $dialog = New-Object System.Windows.Forms.FolderBrowserDialog
    $dialog.Description = "Seleccione la carpeta del JDK (ej: C:\Program Files\Java\jdk-17)"

    if ($dialog.ShowDialog() -eq "OK") {
        $JavaHome = $dialog.SelectedPath
    } else {
        Write-Warning "Ruta no proporcionada."
        Write-Error "Instalación cancelada."
        Show-CloseCountdown "La instalación falló" 1 "White" "Red"
    }
}

#---------- INICIO: Validar Java instalado----------
Write-Host "Validando Java instalado."
$javaExe = "$JavaHome\bin\java.exe"
if (!(Test-Path $javaExe)) {
    Write-Error "Java no esta instalado en la ruta esperada: $javaExe"
    Write-Error "Instale Java 17 (o superior) o proporcione una ruta valida."
    Show-CloseCountdown "La instalación falló" 1 "White" "Red"
}

try {
    $javaVersion = & $javaExe -version 2>&1
    Write-Host "Java detectado correctamente:"
    $javaVersion | ForEach-Object { Write-Host $_ }
}
catch {
    Write-Error "Java existe pero no se pudo ejecutar."
    Show-CloseCountdown "La instalación falló" 1 "White" "Red"
}
#----------Validar JAVA_HOME----------
Write-Host "Validando variable de entorno: JAVA_HOME..."
$currentJavaHome = [Environment]::GetEnvironmentVariable(
    "JAVA_HOME",
    [EnvironmentVariableTarget]::Machine
)

if ($currentJavaHome -ne $JavaHome) {
    Write-Host "Configurando JAVA_HOME -> $JavaHome"

    [Environment]::SetEnvironmentVariable(
        "JAVA_HOME",
        $JavaHome,
        [EnvironmentVariableTarget]::Machine
    )
}
else {
    Write-Host "La variable de entorno: JAVA_HOME ya esta correctamente configurada."
}

#----------Validar PATH (%JAVA_HOME%\bin)----------
Write-Host "Validando variable de entorno: PATH..."
$machinePath = [Environment]::GetEnvironmentVariable(
    "Path",
    [EnvironmentVariableTarget]::Machine
)

$javaBinPath = "$JavaHome\bin"

if ($machinePath -notlike "*$javaBinPath*") {

    Write-Host "Agregando $javaBinPath al PATH..."

    $newPath = if ([string]::IsNullOrWhiteSpace($machinePath)) {
        $javaBinPath
    } else {
        "$machinePath;$javaBinPath"
    }

    [Environment]::SetEnvironmentVariable(
        "Path",
        $newPath,
        [EnvironmentVariableTarget]::Machine
    )
}
else {
    Write-Host "$javaBinPath ya existe en PATH."
}

Write-Host "Validacion de Java completada."
#---------- FIN: Validar Java instalado----------

#----------- INICIO: Validar puerto 8080 disponible ----------
Write-Host "Validando puerto 8080..."
$portInUse = netstat -ano | findstr :8080
if ($portInUse) {
    Write-Warning "El puerto 8080 ya esta en uso."
    Write-Warning "Libere el puerto para continuar."
    Write-Error "Instalacion cancelada."
    Show-CloseCountdown "La instalación falló" 1 "White" "Red"
}
#----------- FIN: Validar puerto 8080 disponible ----------

#----------- INICIO: Validar servicio MySQL ----------
Write-Host "Detectando servicio MySQL..."
if (-not $MysqlService) {
    $mysqlServices = Get-Service | Where-Object {
        $_.Name -match "^MySQL"
    }

    if ($mysqlServices.Count -eq 0) {
        Write-Warning "No se encontro ningun servicio de MySQL."
        Write-Warning "Instale MySQL para continuar."
        Write-Error "Instalacion cancelada."
        Show-CloseCountdown "La instalación falló" 1 "White" "Red"
    }

    if ($mysqlServices.Count -gt 1) {
        Write-Warning "Se encontraron multiples servicios MySQL:"
        $mysqlServices | ForEach-Object { Write-Host "- $($_.Name)" }
    }

    $MysqlService = $mysqlServices[0].Name
}
Write-Host "Usando servicio MySQL: $MysqlService"

#Validar que el servicio se esté ejecutando, si no, iniciar servicio
$mysqlStatus = (Get-Service -Name $MysqlService).Status

if ($mysqlStatus -ne "Running") {
    Write-Warning "El servicio $MysqlService esta detenido. Intentando iniciarlo..."

    Start-Service $MysqlService
    
    $maxRetries = 10
    $retry = 0

    do {
        Start-Sleep -Seconds 3
        $mysqlStatus = (Get-Service -Name $MysqlService).Status

        if ($mysqlStatus -eq "Running") {
            Write-Host "Se inicio el servicio $MysqlService correctamente."
            break
        }

        $retry++
    } while ($retry -lt $maxRetries)

    if ($mysqlStatus -ne "Running") {
        Write-Warning "No se pudo iniciar el servicio $MysqlService despues de $maxRetries intentos."
        Write-Warning "Intente iniciar el servicio $MysqlService manualmente para continuar."
        Write-Error "Instalacion cancelada"
        Show-CloseCountdown "La instalación falló" 1 "White" "Red"
    }
}
#----------- FIN: Validar servicio MySQL ----------

#----------- INICIO: Validar conexion MySQL ----------
#Obtener credenciales de MySQL sino se pasaron como parametro
if ([string]::IsNullOrWhiteSpace($DbUser) -or [string]::IsNullOrWhiteSpace($DbPass)) {
    Write-Host "No se proporcionaron credenciales de la base de datos por parametro. Solicitando..."

    $dbCredentials = Get-Credential -Message "Ingrese las credenciales de la base de datos"

    if ($null -eq $dbCredentials) {
        Write-Warning "No se proporcionaron credenciales de BD."
        Write-Error "Instalacion cancelada."
        Show-CloseCountdown "La instalación falló" 1 "White" "Red"
    }

    $DbUser = $dbCredentials.UserName
    $DbPass = $dbCredentials.GetNetworkCredential().Password
}

Write-Host "Validando cliente MySQL..."
#Obtener la ruta del ejecutable del mysql (mysql.exe)
$mysqlCim = Get-CimInstance Win32_Service -Filter "Name='$MysqlService'"
$rawPath = $MysqlCim.PathName
$exePath = ($rawPath -replace '"', '') -replace '\s--.*$', ''
$binDir = Split-Path $exePath
$mysqlExe = Join-Path $binDir "mysql.exe"

#Realizar prueba de conexion a mysql
if (Test-Path $mysqlExe) {
    Write-Host "Validando conexion a MySQL..."

    $maxRetries = 3
    $retry = 0
    $connected = $false

    $bdArgs = @(
        "-u", $DbUser,
        "-p$DbPass",
        "-h", "localhost",
        "-P", "3306",
        "-e", "SELECT 1;"
    )

    do {
        try {
           $result = & $mysqlExe @bdArgs  2>&1
            if ($LASTEXITCODE -eq 0) {
                Write-Host "Conexion a MySQL exitosa."
                $connected = $true
                break
            }
            else {
                Write-Warning "Fallo de conexion: $result"
            }
        }
        catch {
            Write-Warning "Error al intentar conectar a MySQL."
        }

        $retry++
        if ($retry -lt $maxRetries) {
            Write-Host "Reintentando en 3 segundos..."
            Start-Sleep -Seconds 3
        }
        
    } while ($retry -lt $maxRetries)

    if (-not $connected) {
        Write-Warning "No se pudo establecer conexion con MySQL."
        Write-Warning "Verifique usuario, contraseña y que la BD este activa."
        Write-Error "Instalacion cancelada"
        Show-CloseCountdown "La instalación falló" 1 "White" "Red"
    }
}
else {
    Write-Warning "No se encontro mysql.exe"
    Write-Error "Instalacion cancelada"
    Show-CloseCountdown "La instalación falló" 1 "White" "Red"
}
#----------- FIN: Validar conexion MySQL ----------

# =================
# INSTALACION TOMCAT
# =================
#---------- Copiar ./tomcat/ en el InstallationPath proporcionado ---------
Write-Host "Instalando servidor Tomcat en $InstallationPath..."

if (!(Test-Path $InstallationPath)) {
    New-Item -ItemType Directory -Force -Path $InstallationPath
}
Copy-Item -Recurse -Force ".\tomcat\*" $InstallationPath

#---------- Deply del archivo ROOT.war en InstallationPath/webapps ----------
if (Test-Path "$InstallationPath\webapps\ROOT") {
    Write-Host "Eliminando directorio ROOT anterior..."
    Remove-Item -Recurse -Force "$InstallationPath\webapps\ROOT"
}

Write-Host "Desplegando ROOT.war..."
Copy-Item ".\ROOT.war" "$InstallationPath\webapps\ROOT.war" -Force

#--------- Configurando application.properties externo ---------

$configDir = "$InstallationPath\config-comandero"
$configFile = "$configDir\application.properties"

Write-Host "Creando el directorio config-comandero..."
if (!(Test-Path $configDir)) {
    New-Item -ItemType Directory -Force -Path $configDir
}

Write-Host "Configurando application.properties externo..."

@"
spring.application.name=api-comandero

spring.datasource.url=jdbc:mysql://localhost:3306/g_restaurante
spring.datasource.username=$DbUser
spring.datasource.password=$DbPass

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

server.address=0.0.0.0
server.port=8080

# =========================
# HIKARI CONNECTION POOL
# =========================
spring.datasource.hikari.initialization-fail-timeout=60000
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.validation-timeout=5000
spring.datasource.hikari.maximum-pool-size=15
spring.datasource.hikari.minimum-idle=3
spring.datasource.hikari.connection-test-query=SELECT 1

management.endpoints.web.exposure.include=health
management.endpoint.health.show-details=always
"@ | Out-File -Encoding UTF8 $configFile

#====================
# INSTALACION DE TOMCAT COMO SERVICIO
#====================

Write-Host "Instalando servicio: $ServiceName..."

Set-Location "$InstallationPath\bin"

$service = Get-Service -Name $ServiceName -ErrorAction SilentlyContinue

if ($service) {
    Write-Warning "El servicio $ServiceName ya existe, se eliminara y se instalara uno nuevo"
    if ($service.Status -eq "Running") {
        Write-Host "Deteniendo servicio..."
        Stop-Service -Name $ServiceName -Force
    }
    Write-Host "Eliminando servicio..."
    cmd /c service.bat uninstall $ServiceName
}
Write-Host "Instalando servicio..."
cmd /c service.bat install $ServiceName

#Establecer servicio con inicio automatico
Set-Service -Name $ServiceName -StartupType Automatic

#---------- Agregar la opcion para que el servicio reconozca el application.properties ----------
Write-Host "Configurando Java Options..."

$escapedPath = $InstallationPath -replace '\\', '/'

$newOption = "-Dspring.config.additional-location=file:/$escapedPath/config-comandero/"

# Opciones base de Tomcat (Estas son las Java Options que vienen al instalar manualmente)
$baseOptions = @(
    "-Dcatalina.home=$InstallationPath",
    "-Dcatalina.base=$InstallationPath",
    "-Djava.io.tmpdir=$InstallationPath/temp",
    "-Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager",
    "-Djava.util.logging.config.file=$InstallationPath/conf/logging.properties",
    "-Dsun.io.useCanonCaches=false"
)

# Agregar opción para reconocimiento de application.properties externo
$allOptions = $baseOptions + $newOption
$finalOptions = ($allOptions -join ';')

$cmd = "//US//$ServiceName --JvmOptions=$finalOptions"

Start-Process "$InstallationPath\bin\tomcat10.exe" `
    -ArgumentList $cmd `
    -Wait `
    -NoNewWindow

#---------- Agregando como dependencia el servicio de MySQL ----------
Write-Host "Configurando dependencia con $MysqlService..."

cmd /c "sc config $ServiceName depend= `"$MysqlService/Tcpip/Afd`""

#====================
# CREACION DE REGLA DE ENTRADA EN FIREWALL
#====================
$ruleName = "Tomcat 8080"

Write-Host "Configurando regla de firewall $ruleName..."

$ruleExists = netsh advfirewall firewall show rule name="$ruleName" | findstr "$ruleName"

if (!$ruleExists) {
    cmd /c "netsh advfirewall firewall add rule name=""$ruleName"" dir=in action=allow protocol=TCP localport=8080"
}
else {
    Write-Host "La regla de firewall $ruleName ya existe."
}

#====================
# INICIAR SERVICIO
#====================

Write-Host "Iniciando servicio..."

Start-Service $ServiceName

Write-Host "Verificando estatus de la API..."

$maxRetries = 12
$retry = 0
$ok = $false

do {
    try {
        Write-Host "Intento #$($retry + 1)... esperando respuesta de la API"
        $response = Invoke-RestMethod -Uri "http://localhost:8080/actuator/health" -UseBasicParsing -TimeoutSec 5
        if ($response.status -eq "UP") {
            $ok = $true
            break
        }
    }
    catch {}

    Start-Sleep -Seconds 5
    $retry++

} while ($retry -lt $maxRetries)

if (-not $ok) {
    Write-Warning "La API no respondio correctamente despues de iniciar."
}
else {
    Write-Host "Conexion exitosa despues de: #$($retry + 1) intentos"
    Write-Host "API funcionando correctamente."
}

Write-Host "=== INSTALACION COMPLETADA ==="

Show-CloseCountdown "Instalación completada correctamente" 0 "Green" "Black"