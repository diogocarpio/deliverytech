package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * Cadastrar novo cliente
     */
    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Cliente cliente) {
        try {
            Cliente clienteSalvo = clienteService.cadastrar(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
        }
    }

    /**
     * Listar todos os clientes ativos
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteService.buscarClientesAtivos();
        return ResponseEntity.ok(clientes);
    }

    /**
     * Buscar cliente por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        return cliente.isPresent() ? ResponseEntity.ok(cliente.get())
                                   : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }

    /**
     * Buscar cliente por email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {
        Optional<Cliente> cliente = clienteService.buscarPorEmail(email);
        return cliente.isPresent() ? ResponseEntity.ok(cliente.get())
                                   : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }

    /**
     * Buscar clientes por nome (parte do nome)
     */
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Cliente>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(clienteService.buscarPorNome(nome));
    }

    /**
     * Buscar cliente por telefone
     */
    @GetMapping("/telefone/{telefone}")
    public ResponseEntity<?> buscarPorTelefone(@PathVariable String telefone) {
        Optional<Cliente> cliente = clienteService.buscarPorTelefone(telefone);
        return cliente.isPresent() ? ResponseEntity.ok(cliente.get())
                                   : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }

    /**
     * Buscar clientes com pedidos
     */
    @GetMapping("/com-pedidos")
    public ResponseEntity<List<Cliente>> buscarClientesComPedidos() {
        return ResponseEntity.ok(clienteService.buscarClientesComPedidos());
    }

    /**
     * Buscar clientes por cidade
     */
    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<Cliente>> buscarPorCidade(@PathVariable String cidade) {
        return ResponseEntity.ok(clienteService.buscarPorCidade(cidade));
    }

    /**
     * Contar clientes ativos
     */
    @GetMapping("/ativos/contar")
    public ResponseEntity<Long> contarClientesAtivos() {
        return ResponseEntity.ok(clienteService.contarClientesAtivos());
    }
}
