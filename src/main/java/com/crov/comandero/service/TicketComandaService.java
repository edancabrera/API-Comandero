package com.crov.comandero.service;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.TicketComandaDTO;
import com.crov.comandero.dto.TicketComandaDetalleDTO;
import com.crov.comandero.model.Parametros;
import com.crov.comandero.repository.ParametrosRepository;
import com.crov.comandero.util.TicketFormatter;

@Service
public class TicketComandaService {

    private final PrinterService printerService;
    private final ParametrosRepository parametrosRepository;

    public TicketComandaService(PrinterService printerService, ParametrosRepository parametrosRepository){
        this.printerService = printerService;
        this.parametrosRepository = parametrosRepository;
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
            ticket.append(fmt.lineTextRight("Fecha:", dto.getFecha().toString()));
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
        
            default:
                return parametros.getImpresoraCocina();
        }
    }

    private int obtenerTamanoPapel(String menu, Parametros parametros) {
        switch (menu.toUpperCase()) {
            case "BEBIDA":
                return parametros.getImpresoraBarPapel();
        
            default:
                return parametros.getImpresoraCocinaPapel();
        }
    }
}
