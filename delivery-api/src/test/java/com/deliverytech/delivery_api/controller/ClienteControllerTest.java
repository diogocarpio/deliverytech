package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.ClienteDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ClienteService clienteService;

    @Test
    void deveCadastrarClienteComSucesso() throws Exception {
        ClienteDTO dto = new ClienteDTO("João", "joao@email.com", "12345678", "Rua A, 123 - São Paulo/SP");
        ClienteResponseDTO response = new ClienteResponseDTO(1L, "João", "joao@email.com", "12345678", true);

        when(clienteService.cadastrarCliente(any(ClienteDTO.class))).thenReturn(response);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.email").value("joao@email.com"));
    }

    @Test
    void deveListarClientesComSucesso() throws Exception {
        ClienteResponseDTO cliente = new ClienteResponseDTO(1L, "Maria", "maria@email.com", "987654321", true);
        when(clienteService.listarClientesAtivos()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/clientes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Maria"));
    }

    @Test
    void deveBuscarClientePorIdComSucesso() throws Exception {
        ClienteResponseDTO cliente = new ClienteResponseDTO(1L, "Pedro", "pedro@email.com", "111222333", true);
        when(clienteService.buscarClientePorId(1L)).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Pedro"));
    }

    @Test
    void deveRetornarNotFoundAoBuscarPorIdInexistente() throws Exception {
        when(clienteService.buscarClientePorId(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/clientes/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Cliente não encontrado"));
    }

    @Test
    void deveAtualizarClienteComSucesso() throws Exception {
        ClienteDTO dto = new ClienteDTO("Carlos", "carlos@email.com", "555444333", "Rua A, 123 - São Paulo/SP");
        ClienteResponseDTO atualizado = new ClienteResponseDTO(1L, "Carlos", "carlos@email.com", "555444333", true);

        when(clienteService.atualizarCliente(eq(1L), any(ClienteDTO.class))).thenReturn(atualizado);

        mockMvc.perform(put("/clientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ativo").value(false));
    }
}