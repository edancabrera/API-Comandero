package com.crov.comandero.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class TicketFormatter {
    private final int lineWidth;

    public TicketFormatter(int paperSizeMm){
        this.lineWidth = (paperSizeMm == 80) ? 48: 32;
    }

    public String lineSeparator(char caracter){
        return String.valueOf(caracter).repeat(lineWidth) + "\n";
    }

    public String lineTextRight(String left, String right){
        if(left == null) left = "";
        if(right == null) right = "";

        if(left.length() + right.length() + 1 > lineWidth){
            return left + "\n" + paddingLeft(right) + "\n";
        }

        int spaces = lineWidth - left.length() - right.length();
        return left + " ".repeat(spaces) + right + "\n";

    }

    public String lineThreeText(String left, String center, String right)
    {
        if (left == null) left = "";
        if (center == null) center = "";
        if (right == null) right = "";

        int totalTextLenght = left.length() + center.length() + right.length();

        if(totalTextLenght >= lineWidth) {
            return wrapText(left + " " + center + " " + right);
        }

        int remainingSpaces = lineWidth - totalTextLenght;

        int spaceLeftCenter = remainingSpaces / 2;
        int spaceCenterRight = remainingSpaces - spaceLeftCenter;

        return left
            + " ".repeat(spaceLeftCenter)
            + center
            + " ".repeat(spaceCenterRight)
            + right
            + "\n";
    }

    public String wrapText(String text) {
         if (text == null || text.isBlank()) return "\n";

         StringBuilder result = new StringBuilder();
         String[] words = text.split("\\s+");
         StringBuilder line = new StringBuilder();

         for(String word: words) {
            
            if(line.length() + word.length() + 1 > lineWidth){
                result.append(line).append("\n");
                line.setLength(0);
            }

            line.append(word).append(" ");
         }

         if(!line.isEmpty()) {
            result.append(line).append("\n");
         }
         return result.toString();
    }

    public String center (String text) {
        if (text.length() >= lineWidth) return text + "\n";
        int spaces = (lineWidth - text.length()) / 2;
        return " ".repeat(spaces) + text + "\n";
    }

    private String paddingLeft(String text){
        if(text.length()>= lineWidth) return text;
        return " ".repeat(lineWidth-text.length()) + text;
    }

    public String money(String valor) {
        try {
            BigDecimal bd = new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP);

            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es" , "MX"));
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);

            return nf.format(bd);
        } catch (NumberFormatException e) {
            return "$" + valor;
        }
    }
}