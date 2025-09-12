package com.deliverytech.delivery_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_pedido")
    private String numeroPedido;
    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    private String observacoes;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cliente_id") // Nome da coluna FK no banco
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "restaurante_id") // Nome da coluna FK no banco
    private Restaurante restaurante;
}
