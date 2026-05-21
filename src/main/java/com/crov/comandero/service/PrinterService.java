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

        //Conversión del contenido del ticket a bytes (UTF-8)
        byte[] textBytes = content.getBytes(StandardCharsets.UTF_8);

        //Comandos ESC/POS
        //Avance de papel, (0X0A = LF (Line Feed))
        byte[] feed = new byte[]{
            0x0A, 0x0A, 0x0A, 0x0A,
            0x0A, 0x0A, 0x0A, 0x0A
        };
        //Corte de papel (corte completo)
        byte[] cut = new byte[]{0x1D, 0x56, 0x00};
        //Construcción del mensaje final,
        //Se contstruye un solo arreglo de bytes con [contenido del ticket][avance][corte]
        byte[] finalBytes = new byte[textBytes.length + feed.length + cut.length];

        //Copiar contenido del ticket
        System.arraycopy(textBytes, 0, finalBytes, 0, textBytes.length);
        //Agregar avance de papel después del contenido
        System.arraycopy(feed, 0, finalBytes, textBytes.length, feed.length);
        //Agregar comando de corte final
        System.arraycopy(cut, 0, finalBytes, textBytes.length + feed.length, cut.length);

        //Envio a Impresora
        DocPrintJob job = printer.createPrintJob();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(finalBytes, flavor, null);

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