package com.deliverytech.delivery_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

import com.deliverytech.delivery_api.validation.ValidCEP;

// Classe DTO para representar os dados de um pedido na API
public class PedidoDTO {

    // ID do cliente que fez o pedido — obrigatório (não pode ser nulo)
    @NotNull(message = "ID do cliente é obrigatório")
    private Long clienteId;

    // ID do restaurante onde o pedido foi feito — obrigatório
    @NotNull(message = "ID do restaurante é obrigatório")
    private Long restauranteId;

    // Lista de itens do pedido — deve ter pelo menos um item
    @NotEmpty(message = "Pedido deve ter pelo menos um item")
    private List<ItemPedidoDTO> itens;

    // Endereço de entrega — obrigatório e não pode ser vazio
    @NotBlank(message = "Endereço de entrega é obrigatório")
    private String enderecoEntrega;

    // CEP de entrega — validado pela anotação customizada @ValidCEP
    @ValidCEP
    private String cepEntrega;

    // Forma de pagamento — obrigatório e não pode ser vazio
    @NotBlank(message = "Forma de pagamento é obrigatória")
    private String formaPagamento;

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

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public String getCepEntrega() {
        return cepEntrega;
    }

    public void setCepEntrega(String cepEntrega) {
        this.cepEntrega = cepEntrega;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
