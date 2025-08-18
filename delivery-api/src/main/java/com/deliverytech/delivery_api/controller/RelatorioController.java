package com.deliverytech.delivery_api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.dto.ProdutoMaisVendidoDTO;
import com.deliverytech.delivery_api.dto.VendaPorRestauranteDTO;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/relatorios")
@Tag(name = "Relatorios")
public class RelatorioController {

    @GetMapping("/vendas-por-restaurante")
    public ResponseEntity<List<VendaPorRestauranteDTO>> vendasPorRestaurante(
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate fim) {

        // Exemplo de chamada ao serviço:
        // List<VendaPorRestauranteDTO> vendas = relatorioService.consultarVendas(inicio, fim);

        return ResponseEntity.ok().build(); // Substituir por .body(vendas)
    }

    @GetMapping("/produtos-mais-vendidos")
    public ResponseEntity<List<ProdutoMaisVendidoDTO>> produtosMaisVendidos(
            @RequestParam(required = false) Long restauranteId,
            @RequestParam(defaultValue = "10") int limite) {

        // Chamada para o serviço com filtros opcionais
        // List<ProdutoMaisVendidoDTO> produtos = relatorioService.buscarMaisVendidos(restauranteId, limite);

        return ResponseEntity.ok().build(); // Substituir por .body(produtos)
    }
}
