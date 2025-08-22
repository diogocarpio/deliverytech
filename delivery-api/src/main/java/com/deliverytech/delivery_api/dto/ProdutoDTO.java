package com.deliverytech.delivery_api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Classe DTO usada para transferir dados de Produto via API
public class ProdutoDTO {

    // Nome do produto: não pode ser vazio ou nulo
    // Deve ter entre 2 e 100 caracteres
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    // Descrição do produto: não pode ser vazia ou nula
    // Máximo de 500 caracteres permitidos
    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    private String descricao;

    // Preço do produto: não pode ser nulo
    // Deve ser maior que 0.01 e no máximo 500.00
    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    @DecimalMax(value = "500.00", message = "Preço não pode exceder R$500,00")
    private BigDecimal preco;

    // ID do restaurante ao qual o produto pertence: não pode ser nulo
    @NotNull(message = "ID do restaurante é obrigatório")
    private Long restauranteId;

    // Categoria do produto: não pode ser vazia ou nula
    @NotBlank(message = "Categoria é obrigatória")
    private String categoria;

    // Getter e Setter para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para descricao
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getter e Setter para preco
    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    // Getter e Setter para restauranteId
    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    // Getter e Setter para categoria
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
