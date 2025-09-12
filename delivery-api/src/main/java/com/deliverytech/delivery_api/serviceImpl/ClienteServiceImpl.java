package com.deliverytech.delivery_api.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery_api.dto.ClienteDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.exception.BusinessException;
import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import com.deliverytech.delivery_api.service.ClienteService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ModelMapper modelMapper;

    @Cacheable("clientes")
    public List<ClienteDTO> listarTodos() {
       simulateDelay(); // simular demora
       return clienteRepository.findAll().stream()
            .map(ClienteDTO::new)
            .toList();
    }

    private void simulateDelay() {
        try {
        Thread.sleep(3000);
        } catch (InterruptedException e) {
        // Ignora exceção
        }

    }

    @Override
    public ClienteResponseDTO cadastrarCliente(ClienteDTO dto) {
        // 1️⃣ Verifica se já existe um cliente com o e-mail informado
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email já cadastrado: " + dto.getEmail(), "bussines.exception");
        }

        // 2️⃣ Converte o DTO recebido para a entidade Cliente
        Cliente cliente = modelMapper.map(dto, Cliente.class);

        // 3️⃣ Define o status do cliente como ativo
        cliente.setAtivo(true);

        // 4️⃣ Salva o cliente no banco de dados
        Cliente salvo = clienteRepository.save(cliente);

        // 5️⃣ Converte a entidade salva para um DTO de resposta e retorna
        return modelMapper.map(salvo, ClienteResponseDTO.class);
    }


    @Override
    public Optional<ClienteResponseDTO> buscarClientePorId(Long id) {
        // Busca o cliente pelo ID. Se não encontrar, lança exceção personalizada
        Cliente cliente = clienteRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não Encontrado"));

        // Converte a entidade Cliente para o DTO de resposta e retorna
        return Optional.of(modelMapper.map(cliente, ClienteResponseDTO.class));

    }

    @Override
    public Optional<ClienteResponseDTO> buscarClientePorEmail(String email) {
        // Busca o cliente pelo Email. Se não encontrar, lança exceção personalizada
        Cliente cliente = clienteRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("Email não Encontrado"));

        // Converte a entidade Cliente para o DTO de resposta e retorna
        return Optional.of(modelMapper.map(cliente, ClienteResponseDTO.class));
    }

    @Override
    public ClienteResponseDTO atualizarCliente(Long id, ClienteDTO dto) {
        // Busca o cliente pelo ID. Se não encontrar, lança exceção personalizada
        Cliente cliente = clienteRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não Encontrado"));

        // Verifica se o e-mail foi alterado e se ja esta sendo usado por outro cliente
        if(!cliente.getEmail().equals(dto.getEmail()) &&
            clienteRepository.existsByEmail(dto.getEmail())){
            throw new BusinessException("Email ja cadastrado: " + dto.getEmail(), "bussines.exception");    
        }

        // Atualiza os campos permitidos com os dados do DTO
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());

        // Salva as alterações no banco de dados
        Cliente atualizado = clienteRepository.save(cliente);

        // Converte a entidade atualizada para DTO de resposta e retorna
        return modelMapper.map(atualizado, ClienteResponseDTO.class);
    }

    @Override
    public ClienteResponseDTO ativarDesativarCliente(Long id) {
        // Busca o cliente pelo ID. Se não encontrar, lança exceção personalizada
        Cliente cliente = clienteRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não Encontrado"));

        //Inverte o status atual do cliente (ativo <-> inativo)
        cliente.setAtivo(!cliente.isAtivo());

        //Salva o cliente com o novo status e retorna o DTO de resposta
        return modelMapper.map(clienteRepository.save(cliente), ClienteResponseDTO.class);
    }

    @Override
    public List<ClienteResponseDTO> listarClientesAtivos() {
        // Busca todos os clientes com o status "ativo" no repositório
        return clienteRepository.findByAtivoTrue()
        .stream()
        // Converte cada cliente em ClienteResponseDTO
        .map(c -> modelMapper.map(c, ClienteResponseDTO.class))
        // Coleta os resultados em uma lista
        .collect(Collectors.toList());
    }
}
