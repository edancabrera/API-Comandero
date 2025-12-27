package com.crov.comandero.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crov.comandero.dto.ErrorResponseDTO;
import com.crov.comandero.service.exception.RolNoPermitidoException;
import com.crov.comandero.service.exception.UsuarioInactivoException;
import com.crov.comandero.service.exception.UsuarioNoEncontradoException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> handleUsuarioNoEncontrado(HttpServletRequest request) {
        
        ErrorResponseDTO error = new ErrorResponseDTO(
            HttpStatus.UNAUTHORIZED.value(), 
            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
            "Clave inválida",
            request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(UsuarioInactivoException.class)
    public ResponseEntity<String> handleUsuarioInactivo() {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Usuario inactivo");
    }

    @ExceptionHandler(RolNoPermitidoException.class)
    public ResponseEntity<String> handleRolNoPermitido() {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("El usuario debe ser MESERO o ADMINISTRADOR");
    }
}
