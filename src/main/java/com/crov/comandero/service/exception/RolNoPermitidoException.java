package com.crov.comandero.service.exception;

public class RolNoPermitidoException extends RuntimeException{
    public RolNoPermitidoException() {
        super("El usuario debe ser MESERO o ADMINISTRADOR");
    }
}
