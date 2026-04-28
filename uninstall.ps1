param (
    [string]$InstallationPath = "C:\tomcat",
    [string]$ServiceName = "Tomcat10",
    [string]$RuleName = "Tomcat 8080"
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
    exit $ExitCode
}

Write-Host "=== INICIANDO DESINSTALACION ==="

# ====================
# SERVICIO
# ====================
#Verificar si existe el servicio
Write-Host "Verificando el servicio $ServiceName"
$service = Get-Service -Name $ServiceName -ErrorAction SilentlyContinue

#Sino existe, terminar la desinstalación
if ($service) {
    if ($service.Status -eq "Running") {
        Write-Host "El servicio $ServiceName se esta ejecutando"
        Write-Host "Deteniendo el servicio $ServiceName"
        Stop-Service -Name $ServiceName -Force
        # Esperar a que el servicio se detenga
        $timeout = 10
        while (($service.Status) -ne "Stopped" -and $timeout -gt 0) {
            Start-Sleep -Seconds 1
            $timeout--
        }
    }
    Write-Host "Eliminando servicio..."
    sc.exe delete $ServiceName | Out-Null
} else {
    Write-Warning "No se encontro el servicio $ServiceName"
    Show-CloseCountdown "Cerrando desinstalador" 1 "White" "Red"
}

# ====================
# DIRECTORIO
# ====================

#Verificar si existe el directorio de instalación, si no: notificar y continuar
if ((Test-Path $InstallationPath)) {
    Write-Host "Eliminando carpeta $InstallationPath..."
    Remove-Item $InstallationPath -Recurse -Force
} else {
    Write-Warning "No se encontro el directorio $InstallationPath"
}

# ====================
# FIREWALL
# ====================

#Verificar que existe la regla de firewall
$firewallRule = Get-NetFirewallRule -DisplayName $RuleName -ErrorAction SilentlyContinue

#Sino existe, notificar y continuar. Si existe: eliminar
if($firewallRule){
    Write-Host "Eliminando regla de firewall $RuleName"
    Remove-NetFirewallRule -Name $firewallRule.Name
} else {
    Write-Host "No se pudo eliminar la regla de firewall $RuleName porque no se encontro"
}

Write-Host "=== DESINSTALACION COMPLETADA ==="

Show-CloseCountdown "Desinstalacion completada correctamente" 0 "Green" "Black"