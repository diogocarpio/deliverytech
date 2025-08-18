package com.deliverytech.delivery_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deliverytech.delivery_api.dto.ItemPedidoDTO;
import com.deliverytech.delivery_api.dto.PedidoDTO;
import com.deliverytech.delivery_api.dto.PedidoResponseDTO;
import com.deliverytech.delivery_api.service.PedidoService;
import com.deliverytech.delivery_api.model.StatusPedido;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(
    name = "Pedidos",
    description = "Gerenciamento de pedidos"
)
public class PedidoController {
    
    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
        summary = "Criar pedido",
        description = "Cria um novo pedido com múltiplos itens"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Pedido criado com sucesso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PedidoDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos"
        )
    })
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
