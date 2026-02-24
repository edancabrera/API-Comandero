package com.crov.comandero.service;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.TicketComandaDTO;
import com.crov.comandero.dto.TicketComandaDetalleDTO;

@Service
public class TicketComandaService {
    
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
            imprimirPorMenu(menu, ticket.toString());
        });
    }

    private void imprimirPorMenu(String menu, String contenido) {
        switch (menu) {
            case "COMIDA":
                System.out.println("Enviando a impresora de cocina");
                break;
            case "BEBIDA":
                System.out.println("Enviando a impresora de barra");
                break;
        
            default:
                System.out.println("Enviando a impresora de cocina");
        }
        System.out.println(contenido);
    }
}
