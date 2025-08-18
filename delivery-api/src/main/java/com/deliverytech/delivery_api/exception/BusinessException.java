package com.deliverytech.delivery_api.exception;

public class BusinessException extends RuntimeException{
    //Construtor que recebe a mensagem de erro
    public BusinessException(String msg) {
        super(msg);
    }
}
