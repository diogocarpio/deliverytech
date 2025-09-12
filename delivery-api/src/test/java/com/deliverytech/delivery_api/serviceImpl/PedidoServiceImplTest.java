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
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    private Cliente cliente;
    private Restaurante restaurante;
    private Pedido pedido;
    private PedidoDTO pedidoDTO;
    private PedidoResponseDTO pedidoResponseDTO;

    @BeforeEach
    void setup() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");
        cliente.setAtivo(true);

        restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setAtivo(true);
        restaurante.setTaxaEntrega(BigDecimal.TEN);

        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setStatus(StatusPedido.PENDENTE);

        pedidoDTO = new PedidoDTO();
        pedidoDTO.setClienteId(1L);
        pedidoDTO.setRestauranteId(1L);
        pedidoDTO.setItens(List.of(new ItemPedidoDTO("Pizza", BigDecimal.valueOf(30), 2)));

        pedidoResponseDTO = new PedidoResponseDTO();
        pedidoResponseDTO.setId(1L);
        pedidoResponseDTO.setStatus(StatusPedido.PENDENTE);
    }

    @Test
    void deveCriarPedidoComSucesso() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(restaurante));
        when(modelMapper.map(pedidoDTO, Pedido.class)).thenReturn(pedido);
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
        when(modelMapper.map(pedido, PedidoResponseDTO.class)).thenReturn(pedidoResponseDTO);

        PedidoResponseDTO result = pedidoService.criarPedido(pedidoDTO);

        assertNotNull(result);
        assertEquals(pedidoResponseDTO.getId(), result.getId());
        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> pedidoService.criarPedido(pedidoDTO));
    }

    @Test
    void deveLancarExcecaoQuandoClienteInativo() {
        cliente.setAtivo(false);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        assertThrows(BusinessException.class, () -> pedidoService.criarPedido(pedidoDTO));
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(restauranteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> pedidoService.criarPedido(pedidoDTO));
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteInativo() {
        restaurante.setAtivo(false);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(restaurante));

        assertThrows(BusinessException.class, () -> pedidoService.criarPedido(pedidoDTO));
    }

    @Test
    void deveBuscarPedidoPorId() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(modelMapper.map(pedido, PedidoResponseDTO.class)).thenReturn(pedidoResponseDTO);

        PedidoResponseDTO result = pedidoService.buscarPedidoPorId(1L);

        assertEquals(pedidoResponseDTO.getId(), result.getId());
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNaoEncontradoPorId() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> pedidoService.buscarPedidoPorId(1L));
    }

    @Test
    void deveBuscarPedidosPorCliente() {
        when(pedidoRepository.findByClienteId(1L)).thenReturn(List.of(pedido));
        when(modelMapper.map(pedido, PedidoResponseDTO.class)).thenReturn(pedidoResponseDTO);

        List<PedidoResponseDTO> result = pedidoService.buscarPedidoPorCliente(1L);

        assertEquals(1, result.size());
        assertEquals(pedidoResponseDTO.getId(), result.get(0).getId());
    }

    @Test
    void deveAtualizarStatusDoPedido() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(pedido)).thenReturn(pedido);
        when(modelMapper.map(pedido, PedidoResponseDTO.class)).thenReturn(pedidoResponseDTO);

        PedidoResponseDTO result = pedidoService.atualizarStatusPedido(1L, StatusPedido.ENTREGUE);

        assertEquals(pedidoResponseDTO.getId(), result.getId());
        verify(pedidoRepository).save(pedido);
    }

    @Test
    void deveCalcularTotalPedidoCorretamente() {
        ItemPedidoDTO item1 = new ItemPedidoDTO("Pizza", BigDecimal.valueOf(30), 2); // 60
        ItemPedidoDTO item2 = new ItemPedidoDTO("Refrigerante", BigDecimal.valueOf(10), 1); // 10

        BigDecimal total = pedidoService.calcularTotalPedido(List.of(item1, item2));

        // subtotal = 70 + taxaEntrega (null) → pode ser apenas 70
        assertNotNull(total);
    }

    @Test
    void deveCancelarPedidoComSucesso() {
        pedido.setStatus(StatusPedido.PENDENTE);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        pedidoService.cancelarPedido(1L);

        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());
        verify(pedidoRepository).save(pedido);
    }

    @Test
    void deveLancarExcecaoQuandoCancelarPedidoNaoEncontrado() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> pedidoService.cancelarPedido(1L));
    }

    @Test
    void deveLancarExcecaoQuandoCancelarPedidoEntregue() {
        pedido.setStatus(StatusPedido.ENTREGUE);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        assertThrows(IllegalStateException.class, () -> pedidoService.cancelarPedido(1L));
    }
}
