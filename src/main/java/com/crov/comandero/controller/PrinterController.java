package com.crov.comandero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.crov.comandero.dto.PrinterRequestDTO;
import com.crov.comandero.service.PrinterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PrinterController {

    @Autowired
    private PrinterService printerService;

    @PostMapping("/print")
    public String print(@RequestBody PrinterRequestDTO dto) {
        try {
            printerService.print(dto.getPrinterName(), dto.getContent());
            return "Impresión enviada correctamente";
        } catch (Exception e) {
            return "Error al imprimir: " + e.getMessage();
        }
    }
}