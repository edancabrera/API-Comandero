package com.crov.comandero.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crov.comandero.dto.TicketCobroDTO;
import com.crov.comandero.dto.TicketComandaDTO;
import com.crov.comandero.service.TicketService;

@RestController
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketComandaService) {
        this.ticketService = ticketComandaService;
    }

    @PostMapping("/ticket")
    public ResponseEntity<Void> imprimirTicket( @RequestBody TicketComandaDTO request ) {
            ticketService.generarEImprimir(request);
            return ResponseEntity.ok().build();
        }
    
    @PostMapping("/ticket-cobro")
    public ResponseEntity<Void> imprimirTicketCobro( @RequestBody TicketCobroDTO request ) {
            ticketService.generarEImprimirTicketDeCobro(request);
            return ResponseEntity.ok().build();
        }
}