package com.crov.comandero.service.exception;

public class ClaveInvalidaException extends RuntimeException {
    public ClaveInvalidaException(){
        super("La clave debe tener exactamente 6 caracteres");
    }
}
