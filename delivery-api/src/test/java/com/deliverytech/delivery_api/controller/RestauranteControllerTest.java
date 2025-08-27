package com.deliverytech.delivery_api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.deliverytech.delivery_api.dto.RestauranteDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class RestauranteControllerTest {
/* 
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveListarRestaurantesComSucesso() throws Exception {
        mockMvc.perform(get("/api/restaurantes")
                .accept(MediaType.APPLICATION_JSON)) // Define que espera JSON na resposta
                .andExpect(status().isOk()) // Espera HTTP 200 OK
                .andExpect(jsonPath("$.data").isArray()) // Verifica se campo "data" é array
                .andExpect(jsonPath("$.page").value(0)) // Verifica se página é 0 (primeira página)
                .andExpect(jsonPath("$.totalElements").isNumber()) // Verifica se totalElements é número
                .andDo(print()); // Imprime detalhes da requisição/resposta no console
    }

    @Test
    void deveCriarRestauranteComSucesso() throws Exception {
        RestauranteDTO dto = criarRestauranteDTO(); // Método helper que cria um DTO válido

        mockMvc.perform(post("/api/restaurantes")
                .contentType(MediaType.APPLICATION_JSON) // Define que está enviando JSON
                .content(objectMapper.writeValueAsString(dto))) // Serializa dto para JSON
                .andExpect(status().isCreated()) // Espera HTTP 201 Created
                .andExpect(jsonPath("$.id").isNumber()) // Verifica se resposta tem campo id numérico
                .andExpect(jsonPath("$.nome").value(dto.getNome())); // Verifica se nome retornado é igual ao enviado
    }

    // Método auxiliar para criar um DTO válido para teste
    private RestauranteDTO criarRestauranteDTO() {
        RestauranteDTO dto = new RestauranteDTO();
        dto.setNome("Restaurante Teste");
        dto.setCategoria("Italiana");
        return dto;
    } */
}

