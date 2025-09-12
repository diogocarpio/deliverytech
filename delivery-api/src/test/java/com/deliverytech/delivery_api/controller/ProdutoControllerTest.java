package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.DisponibilidadeDTO;
import com.deliverytech.delivery_api.dto.PedidoInputDTO;
import com.deliverytech.delivery_api.dto.PedidoOutputDTO;
import com.deliverytech.delivery_api.dto.StatusPedidoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoControllerTest {

    private final ProdutoController controller = new ProdutoController();

    @SuppressWarnings("deprecation")
    @Test
    void deveAlterarDisponibilidadeERetornarNoContent() {
        DisponibilidadeDTO dto = new DisponibilidadeDTO(true);

        ResponseEntity<Void> response = controller.alterarDisponibilidade(1L, dto);

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @SuppressWarnings("deprecation")
    @Test
    void deveCriarPedidoERetornarCreated() {
        PedidoInputDTO dto = new PedidoInputDTO(); // Pode preencher os campos se precisar

        ResponseEntity<PedidoOutputDTO> response = controller.criar(dto);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertInstanceOf(PedidoOutputDTO.class, response.getBody());
    }

    @SuppressWarnings("deprecation")
    @Test
    void deveAtualizarStatusERetornarNoContent() {
        StatusPedidoDTO dto = new StatusPedidoDTO("CANCELADO");

        ResponseEntity<Void> response = controller.atualizarStatus(1L, dto);

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
