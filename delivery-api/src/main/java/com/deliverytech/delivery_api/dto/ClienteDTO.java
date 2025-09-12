package com.deliverytech.delivery_api.dto;

import com.deliverytech.delivery_api.model.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// DTO (Data Transfer Object) usado para receber dados de cliente em requisições
public class ClienteDTO {
    
    private Long id;

    // Nome obrigatório, não pode ser vazio ou nulo
    @NotBlank
    private String nome;

    // Email obrigatório, deve estar no formato válido de e-mail
    @Email
    @NotBlank
    private String email;

    // Telefone com 10 ou 11 dígitos (apenas números), validação via expressão regular
    @Pattern(regexp = "\\d{10,11}")
    private String telefone;

    // Endereço obrigatório, não pode ser vazio ou nulo
    @NotBlank
    private String endereco;

    public ClienteDTO(String nome, String email, String telefone, String endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }
    
    public ClienteDTO() {
    }

    // Getters e Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}

