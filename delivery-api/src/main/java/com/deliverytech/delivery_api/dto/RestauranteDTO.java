package com.deliverytech.delivery_api.dto;

public class RestauranteDTO {

    private Long id;        // Campo id, usado na resposta do POST
    private String nome;    // Campo nome, obrigatório
    private String categoria; // Campo categoria, obrigatório

    // Construtor vazio
    public RestauranteDTO() {}

    // Construtor completo (opcional)
    public RestauranteDTO(Long id, String nome, String categoria) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // toString opcional para facilitar debug
    @Override
    public String toString() {
        return "RestauranteDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}

