package com.deliverytech.delivery_api.dto;

public class StatusPedidoDTO {

    private String status;

    public StatusPedidoDTO(String status) {
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
