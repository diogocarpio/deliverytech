package com.deliverytech.delivery_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

// DTO usado para receber dados de um pedido via requisição
public class PedidoDTO {

    // ID do cliente que está fazendo o pedido – obrigatório
    @NotNull
    private Long clienteId;

    // ID do restaurante onde o pedido será feito – obrigatório
    @NotNull
    private Long restauranteId;

    // Endereço para entrega do pedido – obrigatório e não pode ser vazio
    @NotBlank
    private String enderecoEntrega;

    // Lista dos itens do pedido – obrigatório e deve ter ao menos um item
    @Valid // Valida os objetos dentro da lista
    @NotEmpty
    private List<ItemPedidoDTO> itens;

    // Getters e Setters
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }
}
