package com.crov.comandero.service;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.TicketComandaDTO;
import com.crov.comandero.dto.TicketComandaDetalleDTO;
import com.crov.comandero.model.Parametros;
import com.crov.comandero.repository.ParametrosRepository;

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
            StringBuilder ticket = new StringBuilder();

            ticket.append("Mesero: ").append(dto.getMesero()).append("\n");
            ticket.append("=================================\n");
            ticket.append("Mesa: ").append(dto.getMesa()).append("\n");
            ticket.append("=================================\n");
            ticket.append("Fecha: ").append(dto.getFecha()).append("\n");
            ticket.append("=================================\n");

            personasMap.forEach((persona, detalles) -> {
                ticket.append("Persona: ").append(persona).append("\n");
                boolean esCancelacion = "CANCELACION".equalsIgnoreCase(dto.getTipo());

                for(TicketComandaDetalleDTO d: detalles){
                    if(esCancelacion){ticket.append("CANCELADO -> ");}
                    ticket.append(d.getCantidad())
                          .append(" ")
                          .append(d.getNombre())
                          .append("\n");
                    if(d.getComentarios() != null && !d.getComentarios().isBlank()){
                        ticket.append("Comentarios: ")
                              .append(d.getComentarios())
                              .append("\n");
                    }
                }
                ticket.append("----------------------------\n");
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
}
