package com.crov.comandero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crov.comandero.service.PrinterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/print")
public class PrinterController {

    @Autowired
    private PrinterService printerService;

    @PostMapping(consumes = "text/plain")
    public String print(@RequestBody String content) {
        try {
            printerService.print(content);
            return "Impresión enviada correctamente";
        } catch (Exception e) {
            return "Error al imprimir: " + e.getMessage();
        }
    }
}