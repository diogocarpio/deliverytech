package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.ProdutoMaisVendidoDTO;
import com.deliverytech.delivery_api.dto.VendaPorRestauranteDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RelatorioControllerTest {

    private final RelatorioController controller = new RelatorioController();

    @SuppressWarnings("deprecation")
    @Test
    void deveRetornarOkEmVendasPorRestaurante() {
        LocalDate inicio = LocalDate.of(2024, 1, 1);
        LocalDate fim = LocalDate.of(2024, 12, 31);

        ResponseEntity<List<VendaPorRestauranteDTO>> response =
                controller.vendasPorRestaurante(inicio, fim);

        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody()); // ainda não tem service implementado
    }

    @SuppressWarnings("deprecation")
    @Test
    void deveRetornarOkEmProdutosMaisVendidos() {
        ResponseEntity<List<ProdutoMaisVendidoDTO>> response =
                controller.produtosMaisVendidos(1L, 5);

        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody()); // ainda não tem service implementado
    }

    @SuppressWarnings("deprecation")
    @Test
    void deveRetornarOkEmProdutosMaisVendidosSemRestauranteId() {
        // Testando sem passar restauranteId (parâmetro opcional)
        ResponseEntity<List<ProdutoMaisVendidoDTO>> response =
                controller.produtosMaisVendidos(null, 10);

        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
