package com.deliverytech.delivery_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deliverytech.delivery_api.dto.RestauranteDTO;
import com.deliverytech.delivery_api.exception.ConflictException;
import com.deliverytech.delivery_api.exception.EntityNotFoundException;
import com.deliverytech.delivery_api.service.RestauranteService;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/restaurantes")
@Tag(name = "Restaurantes")
public class RestauranteController {
        
    private final RestauranteService service;

    // Injeta o serviço via construtor (boa prática para testes e imutabilidade)
    @Autowired
    public RestauranteController(RestauranteService service) {
        this.service = service;
    }

    // Endpoint GET /api/restaurantes/{id} para buscar restaurante por ID
    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> buscarPorId(@PathVariable Long id) {
        // Busca o restaurante pelo serviço, lança exceção se não encontrado
        RestauranteDTO restaurante = service.buscarPorId(id)
            .orElseThrow(() -> new EntityNotFoundException("Restaurante", id));

        // Retorna 200 OK com o objeto RestauranteDTO no corpo
        return ResponseEntity.ok(restaurante);
    }

    // Endpoint POST /api/restaurantes para criar um novo restaurante
    @PostMapping
    public ResponseEntity<RestauranteDTO> criar(@Valid @RequestBody RestauranteDTO dto) {
        // Verifica se já existe restaurante com o mesmo nome para evitar conflito
        if (service.existePorNome(dto.getNome())) {
            throw new ConflictException("Restaurante", "nome " + dto.getNome());
        }

        // Salva o restaurante via serviço
        RestauranteDTO criado = service.salvar(dto);

        // Retorna 201 Created com o restaurante criado no corpo
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(criado);  
    } 
}
