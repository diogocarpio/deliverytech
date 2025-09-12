package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.ItemPedidoDTO;
import com.deliverytech.delivery_api.dto.PedidoDTO;
import com.deliverytech.delivery_api.dto.PedidoResponseDTO;
import com.deliverytech.delivery_api.model.StatusPedido;
import com.deliverytech.delivery_api.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @Mock
    private PedidoService service;

    @InjectMocks
    private PedidoController controller;

    @SuppressWarnings("deprecation")
    @Test
    void deveCriarPedidoComSucesso() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        PedidoResponseDTO responseDTO = new PedidoResponseDTO(1L, BigDecimal.valueOf(100), StatusPedido.PENDENTE);

        when(service.criarPedido(any(PedidoDTO.class))).thenReturn(responseDTO);

        ResponseEntity<PedidoResponseDTO> response = controller.criar(pedidoDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
        verify(service).criarPedido(eq(pedidoDTO));
    }

    @SuppressWarnings("deprecation")
    @Test
    void deveBuscarPedidoPorId() {
        PedidoResponseDTO responseDTO = new PedidoResponseDTO(1L, BigDecimal.valueOf(200), StatusPedido.CONFIRMADO);

        when(service.buscarPedidoPorId(1L)).thenReturn(responseDTO);

        ResponseEntity<PedidoResponseDTO> response = controller.buscarPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
        verify(service).buscarPedidoPorId(1L);
    }

    @SuppressWarnings("deprecation")
    @Test
    void deveBuscarPedidosPorCliente() {
        List<PedidoResponseDTO> lista = List.of(
                new PedidoResponseDTO(1L, BigDecimal.TEN, StatusPedido.PENDENTE),
                new PedidoResponseDTO(2L, BigDecimal.valueOf(20), StatusPedido.CONFIRMADO)
        );

        when(service.buscarPedidoPorCliente(10L)).thenReturn(lista);

        ResponseEntity<List<PedidoResponseDTO>> response = controller.buscarPorCliente(10L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(service).buscarPedidoPorCliente(10L);
    }

    @SuppressWarnings("deprecation")
    @Test
    void deveAtualizarStatusDoPedido() {
        PedidoResponseDTO atualizado = new PedidoResponseDTO(1L, BigDecimal.valueOf(150), StatusPedido.CANCELADO);

        when(service.atualizarStatusPedido(1L, StatusPedido.CANCELADO)).thenReturn(atualizado);

        ResponseEntity<PedidoResponseDTO> response = controller.atualizarStatus(1L, StatusPedido.CANCELADO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(atualizado, response.getBody());
        verify(service).atualizarStatusPedido(1L, StatusPedido.CANCELADO);
    }

    @SuppressWarnings("deprecation")
    @Test
    void deveCalcularTotalDoPedido() {
        List<ItemPedidoDTO> itens = List.of(
                new ItemPedidoDTO(1L, 2, BigDecimal.valueOf(10)),
                new ItemPedidoDTO(2L, 1, BigDecimal.valueOf(20))
        );

        when(service.calcularTotalPedido(itens)).thenReturn(BigDecimal.valueOf(40));

        ResponseEntity<BigDecimal> response = controller.calcularTotal(itens);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(BigDecimal.valueOf(40), response.getBody());
        verify(service).calcularTotalPedido(itens);
    }

    @SuppressWarnings("deprecation")
    @Test
    void deveCancelarPedido() {
        doNothing().when(service).cancelarPedido(1L);

        ResponseEntity<Void> response = controller.cancelarPedido(1L);

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(service).cancelarPedido(1L);
    }
}
