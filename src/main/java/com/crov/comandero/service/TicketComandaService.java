package com.crov.comandero.service;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.TicketComandaDTO;
import com.crov.comandero.dto.TicketComandaDetalleDTO;

@Service
public class TicketComandaService {

    private final PrinterService printerService;

    public TicketComandaService(PrinterService printerService){
        this.printerService = printerService;
    }
    
    public void generarEImprimir(TicketComandaDTO dto){
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

                for(TicketComandaDetalleDTO d: detalles){
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
            //Mandar a imprimir
            enviarAImpresora(menu, ticket.toString());
        });
    }

    private void enviarAImpresora(String menu, String contenido) {
        String printerName;

        switch (menu) {
            case "COMIDA":
                printerName = "Generic / Text Only";
                break;
            case "BEBIDA":
                printerName = "Generic / Text Only";
                break;
        
            default:
                printerName = "Generic / Text Only";
        }
        
        try {
            printerService.print(printerName, contenido);
        } catch (Exception e) {
            throw new RuntimeException("Error al imprimir en "+ printerName, e);
        }
    }
}
