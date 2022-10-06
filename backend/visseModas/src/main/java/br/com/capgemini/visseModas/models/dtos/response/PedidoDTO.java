package br.com.capgemini.visseModas.models.dtos.response;

import br.com.capgemini.visseModas.models.entities.*;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PedidoDTO {

    private Long id;
    private LocalDate data;
    private String nomeCliente;
    //private ClienteDTO clienteDTO;
    private Situacao situacao;
    private BigDecimal valorTotal;
    private Integer quantidadeTotal;
    private Double percentualDesconto;
    private List<ItemPedidoDTO> listaItens = new ArrayList<>();

    public PedidoDTO(){

    }

    public PedidoDTO(Pedido pedido){

        this.id = pedido.getId();
        this.data = pedido.getData();

        //clienteDTO = new ClienteDTO(pedido.getCliente());
        nomeCliente = pedido.getCliente().getNome();

        this.situacao = pedido.getSituacao();
        this.valorTotal = pedido.getValorTotal();
        this.quantidadeTotal = pedido.getQuantidadeTotal();
        this.percentualDesconto = pedido.getPercentualDesconto();

        this.listaItens = ItemPedidoDTO.converteListaItemPedidoParaListaItemPedidoDTO(pedido.getListaItens());
    }

    public static List<PedidoDTO> converteListaPedidoParaListaPedidoDTO(List<Pedido> listaPedidos){
        return listaPedidos.stream().map(PedidoDTO::new).collect(Collectors.toList());
    }

    public static Page<PedidoDTO> converteListaPedidoParaListaPedidoDTOPaginacao(Page<Pedido> listaPedidos){
        return listaPedidos.map(PedidoDTO::new);
    }

}
