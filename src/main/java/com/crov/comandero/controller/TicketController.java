package com.crov.comandero.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crov.comandero.dto.TicketCobroDTO;
import com.crov.comandero.dto.TicketComandaDTO;
import com.crov.comandero.service.PrinterService;
import com.crov.comandero.service.TicketService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TicketController {
    private final TicketService ticketService;
    private final PrinterService printerService;

    public TicketController(TicketService ticketComandaService, PrinterService printerService) {
        this.ticketService = ticketComandaService;
        this.printerService = printerService;
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
    
    @GetMapping("/printer-configured")
public ResponseEntity<Map<String, Object>> printerConfigured(@RequestParam String printerType) {
    String printerName = null;
    boolean configured = false;

    try {
        printerName = ticketService.verifyPrinter(printerType);
        configured = printerService.findPrinter(printerName) != null;
    } catch (IllegalArgumentException  e) {
        return ResponseEntity.badRequest().body(
            Map.of(
            "printerType", printerType,
            "configured", false,
            "message", e.getMessage()
            )
        );
    } catch (IllegalStateException e ){
        return ResponseEntity.status(404).body(
            Map.of(
            "printerType", printerType,
            "configured", false,
            "message", e.getMessage()
            )
        );
    }

    return ResponseEntity.ok(
        Map.of(
            "printerType", printerType,
            "printerName", printerName,
            "configured", configured
        )
    );
}
    
}