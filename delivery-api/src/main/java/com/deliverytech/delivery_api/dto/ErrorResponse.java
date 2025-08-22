package com.deliverytech.delivery_api.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

// Garante que apenas campos não nulos sejam incluidos no JSON de resposta
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    // Data e hora em que o erro ocorreu
    private final LocalDateTime timestamp;

    // Código HTTP do erro (ex: 400, 404, 500)
    private final int status;

    // Tipo do erro (ex: "Bad Request", "Not Found")
    private final String error;

    // Mensagem explicando o motivo do erro
    private final String message;

    // Caminho da requisição onde o erro ocorreu
    private final String path;

    // Detalhes adicionais, geralmente usados em validações (campo -> mensagem)
    private final Map<String, String> details;

    // Construtor principal: define todos os campos, incluindo os detalhes
    public ErrorResponse(int status, String error, String message,
                        String path, Map<String, String> details) {
        this.timestamp = LocalDateTime.now(); // Define o momento do erro
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.details = details;
    }

    // Construtor alternativo, sem os detalhes (para erros simples)
    public ErrorResponse(int status, String error, String message, String path) {
        this(status, error, message, path, null);
    }

    // Getters para permitir que os dados sejam serializados na resposta JSON
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getDetails() {
        return details;
    }
}

