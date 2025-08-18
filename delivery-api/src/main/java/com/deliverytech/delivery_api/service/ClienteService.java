package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ClienteDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    
    // Cadastra um novo cliente com base nos dados fornecidos (recebe um DTO, retorna outro DTO)
    ClienteResponseDTO cadastrarCliente(ClienteDTO dto);

    // Busca um cliente pelo seu ID
    Optional<ClienteResponseDTO> buscarClientePorId(Long id);

    // Busca um cliente pelo seu e-mail
    Optional<ClienteResponseDTO> buscarClientePorEmail(String email);

    // Atualiza os dados de um cliente existente, identificado pelo ID
    ClienteResponseDTO atualizarCliente(Long id, ClienteDTO dto);

    // Alterna o status do cliente entre ativo e inativo
    ClienteResponseDTO ativarDesativarCliente(Long id);

    // Lista todos os clientes que estão ativos
    List<ClienteResponseDTO> listarClientesAtivos();
}
