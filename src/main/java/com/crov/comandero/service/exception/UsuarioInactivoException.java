package com.crov.comandero.service.exception;

public class UsuarioInactivoException extends RuntimeException{
    public UsuarioInactivoException() {
        super("El usuario está inactivo");
    }

}
