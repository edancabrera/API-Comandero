package com.crov.comandero.model;

public enum GiroComercial {

    ABARROTES("ABARROTES"),
    BOUTIQUE_ROPA("BOUTIQUE - ROPA"),
    CAFETERIA("CAFETERIA"),
    CARNICERIA("CARNICERIA"),
    CIBER("CIBER"),
    DULCERIA("DULCERIA"),
    ELECTRONICA("ELECTRONICA"),
    FARMACIA("FARMACIA"),
    FERRETERIA_TLAPALERIA("FERRETERIA - TLAPALERIA"),
    JOYERIA("JOYERIA"),
    JUGUETERIA("JUGUETERIA"),
    LIBRERIA("LIBRERIA"),
    LICORERIA("LICORERIA"),
    LIMPIEZA("LIMPIEZA"),
    MATERIALES_PARA_CONSTRUCCION("MATERIALES PARA CONSTRUCCION"),
    MINISUPER_AUTOSERVICIO("MINISUPER - AUTOSERVICIO"),
    MUEBLERIAS("MUEBLERIAS"),
    PAPELERIA("PAPELERIA"),
    PINTURAS("PINTURAS"),
    PLASTICOS("PLASTICOS"),
    REFACCIONARIAS("REFACCIONARIAS"),
    RESTAURANTE("RESTAURANTE"),
    TELEFONIA("TELEFONIA"),
    TIENDA_NATURISTA("TIENDA NATURISTA"),
    ZAPATERIA("ZAPATERIA");

    private final String valorBD;

    GiroComercial(String valorBD) {
        this.valorBD = valorBD;
    }

    public String getValorBD() {
        return valorBD;
    }

    public static GiroComercial fromValor(String valor) {
        for (GiroComercial g : values()) {
            if (g.valorBD.equals(valor)) {
                return g;
            }
        }
        throw new IllegalArgumentException("Valor no válido: " + valor);
    }
}