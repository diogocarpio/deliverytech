package com.deliverytech.delivery_api.service;

import java.math.BigDecimal;
import java.util.List;

import com.deliverytech.delivery_api.dto.ItemPedidoDTO;
import com.deliverytech.delivery_api.dto.PedidoDTO;
import com.deliverytech.delivery_api.dto.PedidoResponseDTO;
import com.deliverytech.delivery_api.model.StatusPedido;

public interface PedidoService {

    // Cria um novo pedido com base nos dados fornecidos
    PedidoResponseDTO criarPedido(PedidoDTO dto);

    // Busca um pedido específico pelo seu ID
    PedidoResponseDTO buscarPedidoPorId(Long id);

    // Retorna todos os pedidos associados a um determinado cliente
    List<PedidoResponseDTO> buscarPedidoPorCliente(Long clienteId);

    // Atualiza o status de um pedido (ex: PROCESSAMENTO, ENTREGUE, CANCELADO)
    PedidoResponseDTO atualizarStatusPedido(Long id, StatusPedido status);

    // Calcula o valor total de um pedido com base nos intens informados
    BigDecimal calcularTotalPedido(List<ItemPedidoDTO> itens);

    // Cancela um pedido, se permitido pelas regras de negócio
    public void cancelarPedido(Long id);
}
