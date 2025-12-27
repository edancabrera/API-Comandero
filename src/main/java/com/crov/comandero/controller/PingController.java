package com.crov.comandero.controller;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity <Map<String, Object>> ping() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "200");
        response.put("message", "Conexión exitosa con la API");
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.ok(response);
    }
}
