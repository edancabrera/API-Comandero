package com.crov.comandero.util;

import java.util.regex.Pattern;

public class NumerosLetras {

   Integer counter = 0;
   String value = "";

   private final String[] UNIDADES = {
      "", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "
   };
   private final String[] DECENAS = {
      "diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciséis ",
      "diecisiete ", "dieciocho ", "diecinueve ", "veinte ", "treinta ", "cuarenta ",
      "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "
   };
   private final String[] CENTENAS = {
      "", "ciento ", "doscientos ", "trescientos ", "cuatrocientos ", "quinientos ",
      "seiscientos ", "setecientos ", "ochocientos ", "novecientos "
   };

   public NumerosLetras() {
   }

   public String Convertir(String numero, boolean mayusculas) {
      String literal = "";
      String parte_decimal;
      //si el numero utiliza (.) en lugar de (,) -> se reemplaza
      numero = numero.replace(".", ",");
      //si el numero no tiene parte decimal, se le agrega ,00
      if (numero.indexOf(",") == -1) {
         numero = numero + ",00";
      }
      //se valida formato de entrada -> 0,00 y 999 999 999,00
      if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
         //se divide el numero 0000000,00 -> entero y decimal
         String Num[] = numero.split(",");
         //de da formato al numero decimal
         parte_decimal = "pesos " + Num[1] + "/100 MXN.";
         //se convierte el numero a literal
         if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
            literal = "cero ";
         } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
            literal = getMillones(Num[0]);
         } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
            literal = getMiles(Num[0]);
         } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
            literal = getCentenas(Num[0]);
         } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
            literal = getDecenas(Num[0]);
         } else {//sino unidades -> 9
            literal = getUnidades(Num[0]);
         }
         //devuelve el resultado en mayusculas o minusculas
         if (mayusculas) {
            return (literal + parte_decimal).toUpperCase();
         } else {
            return (literal + parte_decimal);
         }
      } else {//error, no se puede convertir
         return literal = null;
      }
   }

   /* funciones para convertir los numeros a literales */
   private String getUnidades(String numero) {// 1 - 9
      //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
      String num = numero.substring(numero.length() - 1);
      return UNIDADES[Integer.parseInt(num)];
   }

   private String getDecenas(String num) {// 99
      int n = Integer.parseInt(num);
      if (n < 10) {//para casos como -> 01 - 09
         return getUnidades(num);
      } else if (n > 19) {//para 20...99
         String u = getUnidades(num);
         if (u.equals("")) { //para 20,30,40,50,60,70,80,90
            return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
         } else {
            return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
         }
      } else {//numeros entre 11 y 19
         return DECENAS[n - 10];
      }
   }

   private String getCentenas(String num) {// 999 o 099
      if (Integer.parseInt(num) > 99) {//es centena
         if (Integer.parseInt(num) == 100) {//caso especial
            return " cien ";
         } else {
            return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
         }
      } else {//por Ej. 099
         //se quita el 0 antes de convertir a decenas
         return getDecenas(Integer.parseInt(num) + "");
      }
   }

   private String getMiles(String numero) {// 999 999
      //obtiene las centenas
      String c = numero.substring(numero.length() - 3);
      //obtiene los miles
      String m = numero.substring(0, numero.length() - 3);
      String n = "";
      //se comprueba que miles tenga valor entero
      if (Integer.parseInt(m) > 0) {
         n = getCentenas(m);
         return n + "mil " + getCentenas(c);
      } else {
         return "" + getCentenas(c);
      }

   }

   private String getMillones(String numero) { //000 000 000
      //se obtiene los miles
      String miles = numero.substring(numero.length() - 6);
      //se obtiene los millones
      String millon = numero.substring(0, numero.length() - 6);
      String n = "";
      if (millon.length() > 1) {
         n = getCentenas(millon) + "millones ";
      } else {
         n = getUnidades(millon) + "millon ";
      }
      return n + getMiles(miles);
   }

   public String getStringOfNumber(Integer $num) {
      this.counter = $num;
      return doThings($num);
   }

   private String doThings(Integer _counter) {
      //Limite
      if (_counter > 2000000) {
         return "DOS MILLONES";
      }

      switch (_counter) {
         case 0:
            return "CERO";
         case 1:
            return "UN"; //UNO
         case 2:
            return "DOS";
         case 3:
            return "TRES";
         case 4:
            return "CUATRO";
         case 5:
            return "CINCO";
         case 6:
            return "SEIS";
         case 7:
            return "SIETE";
         case 8:
            return "OCHO";
         case 9:
            return "NUEVE";
         case 10:
            return "DIEZ";
         case 11:
            return "ONCE";
         case 12:
            return "DOCE";
         case 13:
            return "TRECE";
         case 14:
            return "CATORCE";
         case 15:
            return "QUINCE";
         case 20:
            return "VEINTE";
         case 30:
            return "TREINTA";
         case 40:
            return "CUARENTA";
         case 50:
            return "CINCUENTA";
         case 60:
            return "SESENTA";
         case 70:
            return "SETENTA";
         case 80:
            return "OCHENTA";
         case 90:
            return "NOVENTA";
         case 100:
            return "CIEN";

         case 200:
            return "DOSCIENTOS";
         case 300:
            return "TRESCIENTOS";
         case 400:
            return "CUATROCIENTOS";
         case 500:
            return "QUINIENTOS";
         case 600:
            return "SEISCIENTOS";
         case 700:
            return "SETECIENTOS";
         case 800:
            return "OCHOCIENTOS";
         case 900:
            return "NOVECIENTOS";

         case 1000:
            return "MIL";

         case 1000000:
            return "UN MILLON";
         case 2000000:
            return "DOS MILLONES";
      }
      if (_counter < 20) {
         //System.out.println(">15");
         return "DIECI" + doThings(_counter - 10);
      }
      if (_counter < 30) {
         //System.out.println(">20");
         return "VEINTI" + doThings(_counter - 20);
      }
      if (_counter < 100) {
         //System.out.println("<100");
         return doThings((int) (_counter / 10) * 10) + " Y " + doThings(_counter % 10);
      }
      if (_counter < 200) {
         //System.out.println("<200");
         return "CIENTO " + doThings(_counter - 100);
      }
      if (_counter < 1000) {
         //System.out.println("<1000");
         return doThings((int) (_counter / 100) * 100) + " " + doThings(_counter % 100);
      }
      if (_counter < 2000) {
         //System.out.println("<2000");
         return "MIL " + doThings(_counter % 1000);
      }
      if (_counter < 1000000) {
         String var = "";
         //System.out.println("<1000000");
         var = doThings((int) (_counter / 1000)) + " MIL";
         if (_counter % 1000 != 0) {
            //System.out.println(var);
            var += " " + doThings(_counter % 1000);
         }
         return var;
      }
      if (_counter < 2000000) {
         return "UN MILLON " + doThings(_counter % 1000000);
      }
      return "";
   }
}