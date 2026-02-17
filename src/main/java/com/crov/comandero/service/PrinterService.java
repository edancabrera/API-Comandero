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

    public void print(String content) throws PrintException {
				
				//Conversión del texto a bytes
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        
        //Búsqueda de la impresora por defecto del sistema
        PrintService printer = PrintServiceLookup.lookupDefaultPrintService();
        if(printer == null){
	        throw new RuntimeException("No hay impresora configurada");
        }

        DocPrintJob job = printer.createPrintJob();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(bytes, flavor, null);

        job.print(doc, null);
    }
}