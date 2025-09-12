package com.deliverytech.delivery_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.deliverytech.delivery_api.model.StatusPedido;

import lombok.Data;

@Data
public class PedidoResponseDTO {
    
    private Long id;
    private String numeroPedido;
    private LocalDateTime dataPedido;
    private StatusPedido status;
    private BigDecimal valorTotal;
    private String observacoes;
    private Long clienteId;
    private Long restauranteId;
    private List<ItemPedidoDTO> itens;

    public PedidoResponseDTO() {
    }

    public PedidoResponseDTO(Long id, BigDecimal valorTotal, StatusPedido status) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.status = status;
    }
}
