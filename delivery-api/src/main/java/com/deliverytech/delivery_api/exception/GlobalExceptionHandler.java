package com.deliverytech.delivery_api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.deliverytech.delivery_api.dto.ErrorResponse;

// Indica que esta classe trata exceções globalmente para todos os controllers
@ControllerAdvice
public class GlobalExceptionHandler {

    // Trata exceções de validação de campos (ex: @NotBlank, @ValidCEP, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {

        // Mapeia os erros de validação: campo -> mensagem
        Map<String, String> details = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> details.put(error.getField(), error.getDefaultMessage()));

        // Cria um objeto de resposta com informações detalhadas do erro
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),       // Código 400
                "Erro de validação",                  // Tipo de erro
                "Campos inválidos na requisição",     // Mensagem geral
                ((ServletWebRequest) request).getRequest().getRequestURI(), // Caminho da requisição
                details                               // Erros específicos por campo
        );

        // Retorna a resposta com status 400 e corpo do erro
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Trata erros quando uma entidade não for encontrada (ex: ID inválido)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(
            EntityNotFoundException ex, WebRequest request) {

        // Você pode implementar com lógica parecida à de cima:
        // Criar ErrorResponse e retornar ResponseEntity com status 404 (NOT_FOUND)

        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        "Entidade não encontrada",
                        ex.getMessage(),
                        ((ServletWebRequest) request).getRequest().getRequestURI(),
                        null
                ),
                HttpStatus.NOT_FOUND
        );
    }

    // Trata conflitos, como duplicidade de dados
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict(
            ConflictException ex, WebRequest request) {

        // Implementar ErrorResponse e retornar com status 409 (CONFLICT)
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        "Conflito de dados",
                        ex.getMessage(),
                        ((ServletWebRequest) request).getRequest().getRequestURI(),
                        null
                ),
                HttpStatus.CONFLICT
        );
    }

    // Trata exceções genéricas não previstas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {

        // Implementar resposta de erro genérica com status 500 (INTERNAL_SERVER_ERROR)
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Erro interno no servidor",
                        ex.getMessage(),
                        ((ServletWebRequest) request).getRequest().getRequestURI(),
                        null
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
