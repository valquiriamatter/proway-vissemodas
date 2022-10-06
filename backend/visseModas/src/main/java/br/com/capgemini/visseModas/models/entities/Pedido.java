package br.com.capgemini.visseModas.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data = LocalDate.now();

    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> listaItens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Situacao situacao = Situacao.ABERTO;

    private BigDecimal valorTotal = BigDecimal.ZERO;
    private Integer quantidadeTotal;
    private Double percentualDesconto = 0.0;



}
