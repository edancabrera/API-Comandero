package com.crov.comandero.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crov.comandero.dto.TicketComandaDTO;
import com.crov.comandero.service.TicketComandaService;

@RestController
public class TicketController {
    private final TicketComandaService ticketComandaService;

    public TicketController(TicketComandaService ticketComandaService) {
        this.ticketComandaService = ticketComandaService;
    }

    @PostMapping("/ticket")
    public ResponseEntity<Void> imprimirTicket( @RequestBody TicketComandaDTO request ) {
            ticketComandaService.generarEImprimir(request);
            return ResponseEntity.ok().build();
        }
}