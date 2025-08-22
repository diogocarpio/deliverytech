package com.deliverytech.delivery_api.exception;

import java.io.Serial;

public class BusinessException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    // Código específico para identificar o tipo de erro
    private final String code;

    // Construtor que recebe a mensagem de erro
    public BusinessException(String message, String code) {
        super(message); // Passa a mensagem para a classe pai RuntimeException
        this.code = code; // Inicializa o código da exceção
    }

    // Método para obter o código do erro 
    public String getCode(){
        return code;
    }
}