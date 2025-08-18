package com.deliverytech.delivery_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deliverytech.delivery_api.dto.RestauranteDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/restaurantes")
@Tag(name = "Restaurantes")
public class RestauranteController {

    @PostMapping
    @Operation(summary = "Cadastrar restaurante")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado com sucesso")
    })
    public ResponseEntity<RestauranteDTO> cadastrar(
            @Valid @RequestBody RestauranteDTO dto) {

        // Aqui entraria a chamada para service.criar(dto) e mapeamento para DTO
        // Exemplo fictício:
        // RestauranteDTO criado = service.criar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dto); // Substituir por "criado" no caso real
    }

    // Outros métodos...
}
