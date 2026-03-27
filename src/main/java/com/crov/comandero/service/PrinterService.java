package com.crov.comandero.service;

import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

import java.nio.charset.StandardCharsets;

@Service
public class PrinterService {

    public void print(String printerName, String content) throws PrintException {
        PrintService printer = findPrinter(printerName);
        if(printer == null){
            throw new RuntimeException("No se encontró la impresora: " + printerName);
        }

        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);

        DocPrintJob job = printer.createPrintJob();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(bytes, flavor, null);

        job.print(doc, null);
    }

    public PrintService findPrinter(String printerName){
        PrintService[] printers = PrintServiceLookup.lookupPrintServices(null, null);
        for(PrintService printer : printers){
            if(printer.getName().equalsIgnoreCase(printerName)){
                return printer;
            }
        }
        return null;
    }
}