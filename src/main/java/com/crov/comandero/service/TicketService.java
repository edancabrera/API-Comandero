package com.crov.comandero.service;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.TicketCobroDTO;
import com.crov.comandero.dto.TicketComandaDTO;
import com.crov.comandero.dto.TicketComandaDetalleDTO;
import com.crov.comandero.model.Empresa;
import com.crov.comandero.model.Parametros;
import com.crov.comandero.repository.EmpresaRepository;
import com.crov.comandero.repository.ParametrosRepository;
import com.crov.comandero.util.TicketFormatter;

@Service
public class TicketService {

    private final PrinterService printerService;
    private final ParametrosRepository parametrosRepository;
    private final EmpresaRepository empresaRepository;

    public TicketService(PrinterService printerService, ParametrosRepository parametrosRepository, EmpresaRepository empresaRepository){
        this.printerService = printerService;
        this.parametrosRepository = parametrosRepository;
        this.empresaRepository = empresaRepository;
    }
    
    public void generarEImprimir(TicketComandaDTO dto){

        Parametros parametros = parametrosRepository.findById(1).orElseThrow(() -> new RuntimeException("No se encontraron parámetros"));

        dto.getDetalle().forEach((menu, personasMap) -> {
            int papel = obtenerTamanoPapel(menu, parametros);
            TicketFormatter fmt = new TicketFormatter(papel);
            
            StringBuilder ticket = new StringBuilder();

            ticket.append(fmt.lineTextRight("Mesero:", dto.getMesero()));
            ticket.append(fmt.lineSeparator('='));
            ticket.append(fmt.lineTextRight("Mesa:", dto.getMesa()));
            ticket.append(fmt.lineSeparator('='));
            ticket.append(fmt.lineTextRight("Fecha:", dto.getFecha()));
            ticket.append(fmt.lineSeparator('='));

            personasMap.forEach((persona, detalles) -> {
                ticket.append("PERSONA: " + persona + "\n");

                boolean esCancelacion = "CANCELACION".equalsIgnoreCase(dto.getTipo());

                for(TicketComandaDetalleDTO d: detalles){
                    if(esCancelacion){
                        ticket.append(fmt.lineThreeText(
                            "CANCELADO ->",
                            d.getCantidad().toString(),
                            d.getNombre()
                        ));
                    } else {
                        ticket.append(fmt.wrapText(d.getCantidad() + " " + d.getNombre()));

                        if(d.getComentarios() != null && !d.getComentarios().isBlank()){
                            ticket.append(fmt.wrapText("Comentarios: " + d.getComentarios()));
                        }
                    }
                }
                ticket.append(fmt.lineSeparator('-'));
            });

            String nombreImpresora = obtenerImpresoraSegunMenu(menu, parametros);

            try {
                printerService.print(nombreImpresora, ticket.toString());
            } catch (Exception e) {
                throw new RuntimeException("Error al imprimir en "+ nombreImpresora, e);
            }
        });
    }

    private String obtenerImpresoraSegunMenu(String menu, Parametros parametros) {

        switch (menu.toUpperCase()) {
            case "BEBIDA":
                return parametros.getImpresoraBar();
            case "ZARANDEADOS":
                return parametros.getImpresoraAsador();
        
            default:
                return parametros.getImpresoraCocina();
        }
    }

    private int obtenerTamanoPapel(String menu, Parametros parametros) {
        switch (menu.toUpperCase()) {
            case "BEBIDA":
                return parametros.getImpresoraBarPapel();
            case "ZARANDEADOS":
                return parametros.getImpresoraAsadorPapel();
        
            default:
                return parametros.getImpresoraCocinaPapel();
        }
    }

    public void generarEImprimirTicketDeCobro(TicketCobroDTO dto) {
        Parametros parametros = parametrosRepository.findById(1).orElseThrow(() -> new RuntimeException("No se encontraron parámetros"));

        Empresa empresa = empresaRepository.findById(1).orElseThrow(() -> new RuntimeException("No se encontraró empresa"));


        TicketFormatter fmt = new TicketFormatter(parametros.getImpresoraAdminPapel());

        StringBuilder ticket = new StringBuilder();

        double iva = 0.0;

        ticket.append(fmt.center(empresa.getNombreComercial()));
        ticket.append(fmt.center(empresa.getDireccion()));
        ticket.append(fmt.center(empresa.getColonia() + " C.P. " + empresa.getCp()));
        ticket.append(fmt.center(empresa.getMunicipio()+", "+ empresa.getEstado()));

        if(Boolean.TRUE.equals(parametros.getMostrarTelefono())){
            ticket.append(fmt.center("Tel. y WhatsApp: " + empresa.getCel()));
            ticket.append(fmt.center("Telefono: " + empresa.getTel()));
        }

        ticket.append(fmt.lineSeparator('='));
        ticket.append(fmt.lineTextRight("Mesero:", dto.getMesero()));
        ticket.append(fmt.lineTextRight("Mesa:", dto.getMesa()));
        ticket.append(fmt.lineTextRight("Fecha:", dto.getFecha()));

        if(Boolean.FALSE.equals(parametros.getOcultarFolio())){
            ticket.append(fmt.lineTextRight("Folio:", ""));
        }

        ticket.append(fmt.lineSeparator('='));

        if(Boolean.TRUE.equals(parametros.getMostrarPrecioUnitario())){
            ticket.append(fmt.lineThreeText("Cantidad","Precio" ,"Total"));
            for (var detalle : dto.getDetalle()) {
                ticket.append(fmt.lineTextRight(detalle.getNombre(), null));
                ticket.append(fmt.lineThreeText(
                        detalle.getCantidad().toString(),
                        fmt.money(String.valueOf(detalle.getPrecioUnitario())),
                        fmt.money(String.valueOf(detalle.getSubtotal()))
                ));

                double porcentajeIva = detalle.getIva() / 100.0;
                double detalleIva = porcentajeIva * detalle.getPrecioUnitario();
                iva += detalle.getCantidad() * detalleIva;
            }
        } else {
            ticket.append(fmt.lineTextRight("Cantidad", "Total"));
            for (var detalle : dto.getDetalle()) {
                ticket.append(fmt.lineTextRight(detalle.getNombre(), null));
                ticket.append(fmt.lineTextRight(
                        detalle.getCantidad().toString(),
                        fmt.money(String.valueOf(detalle.getSubtotal()))
                ));

                double porcentajeIva = detalle.getIva() / 100.0;
                double detalleIva = porcentajeIva * detalle.getPrecioUnitario();
                iva += detalle.getCantidad() * detalleIva;
            }
        }

        ticket.append(fmt.lineSeparator('='));

        if(Boolean.TRUE.equals(parametros.getMostrarImpuestos())){
            double subtotal = dto.getTotal() - iva;
            ticket.append(fmt.lineTextRight("Subtotal: ", fmt.money(String.valueOf(subtotal))));
            ticket.append(fmt.lineTextRight("IVA: ", fmt.money(String.valueOf(iva))));
        } 

        ticket.append(fmt.lineTextRight("Total: ", fmt.money(dto.getTotal().toString())));
        ticket.append(fmt.lineSeparator('='));

        if(Boolean.TRUE.equals(parametros.getMostrarPorpinaSugerida())){
            ticket.append(fmt.wrapText("PROPINA RECOMANDADA: "));
            ticket.append(fmt.lineThreeText("10%", "15%", "20%"));
            Double[] propina = new Double[] { dto.getTotal() * 0.10, dto.getTotal() * 0.15, dto.getTotal() * 0.20 };
            ticket.append(fmt.lineThreeText(
                fmt.money(propina[0].toString()), 
                fmt.money(propina[1].toString()), 
                fmt.money(propina[2].toString())
            ));
            ticket.append(fmt.lineSeparator('='));
        }

        ticket.append(fmt.wrapText("CROV RESTAURANTE "));
        ticket.append(fmt.wrapText("Total en pesos, eventualmente "));

        try {
                printerService.print(parametros.getImpresoraAdmin(), ticket.toString());
            } catch (Exception e) {
                throw new RuntimeException("Error al imprimir en "+ parametros.getImpresoraAdmin(), e);
            }
    }
}
