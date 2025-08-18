package com.deliverytech.delivery_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Classe para tratamento global de exceções na aplicação
@ControllerAdvice
public class GlobalExceptionHandler {

    // Trata exceções do tipo BusinessException
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusiness(BusinessException ex) {
        // Retorna HTTP 400 (Bad Request) com a mensagem da exceção
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    // Trata exceções do tipo EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        // Retorna HTTP 404 (Not Found) com a mensagem da exceção
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    // Outros handlers para exceções diferentes podem ser adicionados aqui
}
