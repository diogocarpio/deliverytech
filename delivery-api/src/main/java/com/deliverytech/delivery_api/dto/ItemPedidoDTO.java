package com.deliverytech.delivery_api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// DTO que representa um item dentro de um pedido
public class ItemPedidoDTO {

    // ID do produto que está sendo adicionado ao pedido — obrigatório
    @NotNull(message = "ID do produto é obrigatório")
    private Long produtoId;

    // Quantidade do produto no pedido — obrigatória
    // Deve ser no mínimo 1 e no máximo 99 unidades
    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade mínima é 1")
    @Max(value = 99, message = "Quantidade máxima é 99")
    private Integer quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal subtotal;

    // Campo opcional para observações adicionais do item (ex: "sem cebola")
    private String observacao;

    // Getters e Setters
    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
