package br.com.capgemini.visseModas.models.dtos.request_form;

import br.com.capgemini.visseModas.models.dtos.response.ItemPedidoDTO;
import br.com.capgemini.visseModas.models.entities.Cliente;
import br.com.capgemini.visseModas.models.entities.Pedido;
import br.com.capgemini.visseModas.services.ClienteService;
import br.com.capgemini.visseModas.services.PedidoService;
import br.com.capgemini.visseModas.services.ProdutoService;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
public class PedidoForm {

    @NotNull
    private Long idCliente;
    @DecimalMax("100.00")
    private Double percentualDesconto;
    @DecimalMax("100000.00")
    private BigDecimal valorTotal;
    @Min(1)
    @Max(99)
    private Integer quantidadeTotal;

    private List<ItemPedidoForm> listaItens = new ArrayList<>();

    public PedidoForm(){

    }

    public Pedido convertePedidoFormParaPedido(ClienteService clienteService, ProdutoService produtoService, PedidoService pedidoService){

        Cliente cliente = clienteService.buscarPorId(idCliente);
        ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setPercentualDesconto(percentualDesconto);
        pedido.setValorTotal(valorTotal);
        pedido.setQuantidadeTotal(quantidadeTotal);
        pedido.setListaItens(itemPedidoDTO.converterListaItemPedidoDTOParaListaItemPedido(produtoService, pedido,listaItens));

        return pedido;
    }





}