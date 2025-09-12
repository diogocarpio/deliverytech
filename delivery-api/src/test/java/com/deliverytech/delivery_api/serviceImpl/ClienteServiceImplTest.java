package com.deliverytech.delivery_api.serviceImpl;

import com.deliverytech.delivery_api.dto.ClienteDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.exception.BusinessException;
import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;
    private ClienteDTO clienteDTO;
    private ClienteResponseDTO clienteResponseDTO;

    @BeforeEach
    void setup() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");
        cliente.setEmail("joao@email.com");
        cliente.setTelefone("123456789");
        cliente.setEndereco("Rua A");
        cliente.setAtivo(true);

        clienteDTO = new ClienteDTO();
        clienteDTO.setNome("João");
        clienteDTO.setEmail("joao@email.com");
        clienteDTO.setTelefone("123456789");
        clienteDTO.setEndereco("Rua A");

        clienteResponseDTO = new ClienteResponseDTO();
        clienteResponseDTO.setId(1L);
        clienteResponseDTO.setNome("João");
        clienteResponseDTO.setEmail("joao@email.com");
        clienteResponseDTO.setTelefone("123456789");
        clienteResponseDTO.setEndereco("Rua A");
        clienteResponseDTO.setAtivo(true);
    }

    @Test
    void deveListarTodosClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<ClienteDTO> result = clienteService.listarTodos();

        assertEquals(1, result.size());
        assertEquals(cliente.getEmail(), result.get(0).getEmail());
        verify(clienteRepository).findAll();
    }

    @Test
    void deveCadastrarClienteComSucesso() {
        when(clienteRepository.existsByEmail(clienteDTO.getEmail())).thenReturn(false);
        when(modelMapper.map(clienteDTO, Cliente.class)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(modelMapper.map(cliente, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

        ClienteResponseDTO result = clienteService.cadastrarCliente(clienteDTO);

        assertEquals(clienteResponseDTO.getEmail(), result.getEmail());
        verify(clienteRepository).save(cliente);
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaCadastrado() {
        when(clienteRepository.existsByEmail(clienteDTO.getEmail())).thenReturn(true);

        assertThrows(BusinessException.class, () -> clienteService.cadastrarCliente(clienteDTO));
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void deveBuscarClientePorIdComSucesso() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(modelMapper.map(cliente, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

        Optional<ClienteResponseDTO> result = clienteService.buscarClientePorId(1L);

        assertTrue(result.isPresent());
        assertEquals(clienteResponseDTO.getEmail(), result.get().getEmail());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontradoPorId() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> clienteService.buscarClientePorId(1L));
    }

    @Test
    void deveBuscarClientePorEmailComSucesso() {
        when(clienteRepository.findByEmail(cliente.getEmail())).thenReturn(Optional.of(cliente));
        when(modelMapper.map(cliente, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

        Optional<ClienteResponseDTO> result = clienteService.buscarClientePorEmail(cliente.getEmail());

        assertTrue(result.isPresent());
        assertEquals(clienteResponseDTO.getEmail(), result.get().getEmail());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontradoPorEmail() {
        when(clienteRepository.findByEmail(cliente.getEmail())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> clienteService.buscarClientePorEmail(cliente.getEmail()));
    }

    @Test
    void deveAtualizarClienteComSucesso() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.existsByEmail(clienteDTO.getEmail())).thenReturn(false);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(modelMapper.map(cliente, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

        ClienteResponseDTO result = clienteService.atualizarCliente(1L, clienteDTO);

        assertEquals(clienteResponseDTO.getEmail(), result.getEmail());
        verify(clienteRepository).save(cliente);
    }

    @Test
    void deveLancarExcecaoQuandoAtualizarClienteComEmailJaUsado() {
        ClienteDTO novoDTO = new ClienteDTO();
        novoDTO.setNome("Maria");
        novoDTO.setEmail("novo@email.com");
        novoDTO.setTelefone("987654321");
        novoDTO.setEndereco("Rua B");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.existsByEmail(novoDTO.getEmail())).thenReturn(true);

        assertThrows(BusinessException.class, () -> clienteService.atualizarCliente(1L, novoDTO));
    }

    @Test
    void deveAtivarDesativarCliente() {
        cliente.setAtivo(true);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(modelMapper.map(cliente, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

        ClienteResponseDTO result = clienteService.ativarDesativarCliente(1L);

        assertEquals(clienteResponseDTO.getEmail(), result.getEmail());
        verify(clienteRepository).save(cliente);
    }

    @Test
    void deveListarClientesAtivos() {
        when(clienteRepository.findByAtivoTrue()).thenReturn(List.of(cliente));
        when(modelMapper.map(cliente, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

        List<ClienteResponseDTO> result = clienteService.listarClientesAtivos();

        assertEquals(1, result.size());
        assertEquals(clienteResponseDTO.getEmail(), result.get(0).getEmail());
        verify(clienteRepository).findByAtivoTrue();
    }
}
