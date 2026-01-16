package com.crov.comandero.model;

public enum Impuesto {

    IVA_16("16"),
    IVA_8("8"),
    IVA_0("0"),
    EXENTA("EXENTA");

    private final String dbValue;

    Impuesto(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static Impuesto fromDbValue(String value) {
        for (Impuesto i : values()) {
            if (i.dbValue.equals(value)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Impuesto no soportado: " + value);
    }

    public double getIva() {
        return switch (this) {
            case IVA_16 -> 16;
            case IVA_8 -> 8;
            case IVA_0, EXENTA -> 0;
        };
    }
}