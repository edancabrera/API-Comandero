package com.crov.comandero.dto;

public class PrinterRequestDTO {
    private String printerName;
    private String content;

    public String getPrinterName() {
        return printerName;
    }
    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}