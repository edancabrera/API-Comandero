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

        ticket.append(fmt.center(empresa.getNombreComercial()));
        ticket.append(fmt.center(empresa.getDireccion()));
        ticket.append(fmt.center(empresa.getColonia() + " C.P. " + empresa.getCp()));
        ticket.append(fmt.center(empresa.getMunicipio()+", "+ empresa.getEstado()));
        ticket.append(fmt.lineSeparator('='));
        ticket.append(fmt.lineTextRight("Mesero:", dto.getMesero()));
        ticket.append(fmt.lineTextRight("Mesa:", dto.getMesa()));
        ticket.append(fmt.lineTextRight("Fecha:", dto.getFecha()));
        ticket.append(fmt.lineSeparator('='));
        ticket.append(fmt.lineTextRight("Cantidad", "Total"));
        dto.getDetalle().forEach(detalle -> {
            ticket.append(fmt.lineTextRight(detalle.getNombre(), null));
            ticket.append(fmt.lineTextRight(detalle.getCantidad().toString(), detalle.getSubtotal().toString()));
        });
        ticket.append(fmt.lineSeparator('='));
        ticket.append(fmt.lineTextRight("Total: ", dto.getTotal().toString()));
        ticket.append(fmt.lineSeparator('='));
        ticket.append(fmt.wrapText("PROPINA RECOMANDADA: "));
        ticket.append(fmt.lineThreeText("10%", "15%", "20%"));
        Double[] propina = new Double[] { dto.getTotal() * 0.10, dto.getTotal() * 0.15, dto.getTotal() * 0.20 };
        ticket.append(fmt.lineThreeText(propina[0].toString(), propina[1].toString(), propina[2].toString()));
        ticket.append(fmt.lineSeparator('='));
        ticket.append(fmt.wrapText("CROV RESTAURANTE "));
        ticket.append(fmt.wrapText("Total en pesos, eventualmente "));

        try {
                printerService.print(parametros.getImpresoraAdmin(), ticket.toString());
            } catch (Exception e) {
                throw new RuntimeException("Error al imprimir en "+ parametros.getImpresoraAdmin(), e);
            }
    }
}
