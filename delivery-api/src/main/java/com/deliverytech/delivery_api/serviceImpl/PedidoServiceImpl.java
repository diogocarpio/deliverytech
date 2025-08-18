package com.deliverytech.delivery_api.serviceImpl;

import com.deliverytech.delivery_api.dto.ItemPedidoDTO;
import com.deliverytech.delivery_api.dto.PedidoDTO;
import com.deliverytech.delivery_api.dto.PedidoResponseDTO;
import com.deliverytech.delivery_api.exception.BusinessException;
import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.model.Pedido;
import com.deliverytech.delivery_api.model.Restaurante;
import com.deliverytech.delivery_api.model.StatusPedido;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import com.deliverytech.delivery_api.repository.PedidoRepository;
import com.deliverytech.delivery_api.repository.RestauranteRepository;
import com.deliverytech.delivery_api.service.PedidoService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ModelMapper modelMapper;
    
    private final ClienteRepository clienteRepository;

    private final RestauranteRepository restauranteRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ModelMapper modelMapper,
    ClienteRepository clienteRepository,
    RestauranteRepository restauranteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.modelMapper = modelMapper;
        this.clienteRepository = clienteRepository;
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public PedidoResponseDTO criarPedido(PedidoDTO dto) {
        // Busca o cliente pelo ID informado no DTO
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
        .orElseThrow(() -> new EntityNotFoundException("Cliente não Encontrado"));

        // Verifica se o cliente esta ativo
        if(!cliente.isAtivo()) {
            throw new BusinessException("Cliente inativo");
        }

        // Busca o restaurante pelo ID informado no DTO
        Restaurante restaurante = restauranteRepository.findById(dto.getRestauranteId())
        .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado"));

        // Verifica se o restaurante esta ativo
        if(!restaurante.isAtivo()) {
            throw new BusinessException("Restaurante indisponível");
        }

        Pedido pedido = modelMapper.map(dto, Pedido.class);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setValorTotal(calcularTotalPedido(dto.getItens()));
        pedido.setStatus(StatusPedido.ENTREGUE);
        pedido.setDataPedido(LocalDateTime.now());

        Pedido salvo = pedidoRepository.save(pedido);
        return modelMapper.map(salvo, PedidoResponseDTO.class);
    }

    @Override
    public PedidoResponseDTO buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com ID: " + id));
        return modelMapper.map(pedido, PedidoResponseDTO.class);
    }

    @Override
    public List<PedidoResponseDTO> buscarPedidoPorCliente(Long clienteId) {
        List<Pedido> pedidos = pedidoRepository.findByClienteId(clienteId);
        return pedidos.stream()
                .map(p -> modelMapper.map(p, PedidoResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PedidoResponseDTO atualizarStatusPedido(Long id, StatusPedido status) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com ID: " + id));

        pedido.setStatus(status);
        Pedido atualizado = pedidoRepository.save(pedido);

        return modelMapper.map(atualizado, PedidoResponseDTO.class);
    }

    //@Override
    //public BigDecimal calcularTotalPedido(List<ItemPedidoDTO> itens) {
    //    return itens.stream()
    //            .map(i -> i.getPrecoUnitario().multiply(BigDecimal.valueOf(i.getQuantidade())))
    //            .reduce(BigDecimal.ZERO, BigDecimal::add);
    //}

    @Override
    public BigDecimal calcularTotalPedido(List<ItemPedidoDTO> itensPedido) {
        Restaurante restaurante = new Restaurante();

        BigDecimal subtotal = itensPedido.stream()
        .map(ItemPedidoDTO::getSubtotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal taxaEntrega = restaurante.getTaxaEntrega();
        
        BigDecimal total = subtotal.add(taxaEntrega);

        return total;
    }

    @Override
    public void cancelarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com ID: " + id));

        if (pedido.getStatus() == StatusPedido.ENTREGUE) {
            throw new IllegalStateException("Não é possível cancelar um pedido já entregue.");
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        pedidoRepository.save(pedido);
    }
}
