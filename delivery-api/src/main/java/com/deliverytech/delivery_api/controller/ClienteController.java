package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.ClienteDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.service.ClienteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService service;

    // Construtor que recebe a dependência do serviço
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @CacheEvict(value = "clientes", allEntries = true)
    @GetMapping("/clientes/cache/limpar")
    public ResponseEntity<Void> limparCache() {
       return ResponseEntity
               .noContent().build();
    }

    // Endpoint POST para cadastrar um novo cliente
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrar(@Valid @RequestBody ClienteDTO dto) {
        logger.info(" Cadastra o cliente e retorna HTTP 201 (Created)");
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(service.cadastrarCliente(dto));
    }

    /**
     * Listar todos os clientes ativos
     */
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        logger.info("Lista todos os clientes ativos");
        List<ClienteResponseDTO> clientes = service.listarClientesAtivos();
        return ResponseEntity.ok(clientes);
    }

    /**
     * Buscar cliente por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<ClienteResponseDTO> cliente = service.buscarClientePorId(id);
        return cliente.isPresent() ? ResponseEntity.ok(cliente.get())
                                   : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }

    /**
     * Buscar cliente por email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {
        Optional<ClienteResponseDTO> cliente = service.buscarClientePorEmail(email);
        return cliente.isPresent() ? ResponseEntity.ok(cliente.get())
                                   : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }

    /**
     * Atualizar cliente por ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
        try {
            ClienteResponseDTO clienteAtualizado = service.atualizarCliente(id, dto);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
    }

    /**
     * Ativar ou desativar cliente
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> ativarDesativar(@PathVariable Long id) {
        try {
            ClienteResponseDTO cliente = service.ativarDesativarCliente(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
    }

}
