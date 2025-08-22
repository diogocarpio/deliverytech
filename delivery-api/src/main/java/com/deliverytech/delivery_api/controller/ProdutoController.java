package com.deliverytech.delivery_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deliverytech.delivery_api.dto.DisponibilidadeDTO;
import com.deliverytech.delivery_api.dto.PedidoInputDTO;
import com.deliverytech.delivery_api.dto.PedidoOutputDTO;
import com.deliverytech.delivery_api.dto.StatusPedidoDTO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
/* 
    @GetMapping
    @Operation(summary = "Listar produtos")
    public ResponseEntity<PagedResponse<ProdutoDTO>> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Long categoriaId,
            Pageable pageable) {

        // Chamada ao serviço que retorna lista paginada de produtos
        // Exemplo fictício:
        // Page<Produto> page = produtoService.listar(nome, categoriaId, pageable);
        // PagedResponse<ProdutoDTO> resposta = PagedResponse.of(page.map(mapper::toDto));

        // Retorno de exemplo (placeholder):
        return ResponseEntity.ok().build();
    }*/

    @PatchMapping("/{id}/disponibilidade")
    @Operation(summary = "Alterar disponibilidade")
    public ResponseEntity<Void> alterarDisponibilidade(
            @PathVariable Long id,
            @RequestBody DisponibilidadeDTO dto) {

        // Aqui entraria a chamada ao serviço, ex:
        // service.alterarDisponibilidade(id, dto.getDisponivel());

        return ResponseEntity.noContent().build(); // HTTP 204 sem corpo
    }

    @PostMapping
    public ResponseEntity<PedidoOutputDTO> criar(
            @Valid @RequestBody PedidoInputDTO dto) {

        // Chamada ao serviço para criar o pedido com os itens recebidos
        // Exemplo:
        // PedidoOutputDTO novoPedido = pedidoService.criar(dto);

        PedidoOutputDTO novoPedido = new PedidoOutputDTO(); // Placeholder

        return ResponseEntity
                .status(HttpStatus.CREATED)   // Retorna 201 Created
                .body(novoPedido);            // Corpo da resposta com o novo pedido
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(
            @PathVariable Long id,
            @RequestBody StatusPedidoDTO dto) {

        // Lógica para atualizar o status do pedido
        // Exemplo:
        // pedidoService.atualizarStatus(id, dto.getStatus());

        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

}

