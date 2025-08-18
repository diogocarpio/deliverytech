package com.deliverytech.delivery_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deliverytech.delivery_api.dto.ItemPedidoDTO;
import com.deliverytech.delivery_api.dto.PedidoDTO;
import com.deliverytech.delivery_api.dto.PedidoResponseDTO;
import com.deliverytech.delivery_api.service.PedidoService;
import com.deliverytech.delivery_api.model.StatusPedido;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {
    
    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    // Cria um novo pedido
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criar(@Valid @RequestBody PedidoDTO dto) {
        PedidoResponseDTO criado = service.criarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // Busca pedido pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        PedidoResponseDTO pedido = service.buscarPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    // Lista todos os pedidos de um cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PedidoResponseDTO>> buscarPorCliente(@PathVariable Long clienteId) {
        List<PedidoResponseDTO> pedidos = service.buscarPedidoPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);
    }

    // Atualiza status de um pedido
    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusPedido status) {  // Recebe o novo status como query param
        PedidoResponseDTO atualizado = service.atualizarStatusPedido(id, status);
        return ResponseEntity.ok(atualizado);
    }

    // Calcula o total de um pedido com base nos itens enviados
    @PostMapping("/calcular-total")
    public ResponseEntity<BigDecimal> calcularTotal(@Valid @RequestBody List<ItemPedidoDTO> itens) {
        BigDecimal total = service.calcularTotalPedido(itens);
        return ResponseEntity.ok(total);
    }


    // Cancela um pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id) {
        service.cancelarPedido(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
