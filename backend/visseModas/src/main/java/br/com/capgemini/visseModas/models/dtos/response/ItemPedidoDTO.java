package br.com.capgemini.visseModas.models.dtos.response;

import br.com.capgemini.visseModas.models.dtos.request_form.ItemPedidoForm;
import br.com.capgemini.visseModas.models.entities.ItemPedido;
import br.com.capgemini.visseModas.models.entities.Pedido;
import br.com.capgemini.visseModas.models.entities.Produto;
import br.com.capgemini.visseModas.services.PedidoService;
import br.com.capgemini.visseModas.services.ProdutoService;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ItemPedidoDTO {

    private Long id;
    private Long idProduto;
    private Long idPedido;
    private Integer quantidade;
    private BigDecimal valorTotal;

    public ItemPedidoDTO(){

    }

    public ItemPedidoDTO(ItemPedido itemPedido){
        this.id = itemPedido.getId();
        this.idProduto = itemPedido.getProduto().getId();
        this.idPedido = itemPedido.getPedido().getId();
        this.quantidade = itemPedido.getQuantidade();
        this.valorTotal = itemPedido.getValorPorItem();
    }

    public static List<ItemPedidoDTO> converteListaItemPedidoParaListaItemPedidoDTO(List<ItemPedido> listaItemPedido){
        return listaItemPedido.stream().map(ItemPedidoDTO::new).collect(Collectors.toList());
    }

//    public List<ItemPedido> converterListaItemPedidoDTOParaListaItemPedido(ProdutoService produtoService, PedidoService pedidoService, List<ItemPedidoForm> lista) {
//
//        Produto produto = produtoService.buscarPorId(idProduto);
//        Pedido pedido = pedidoService.buscarPorId(idPedido);
//
//        ItemPedido itemPedido = new ItemPedido();
//
//        List<ItemPedido> listaItens = lista.stream()
//                .map(itemPedidoForm -> {
//                    itemPedido.setProduto(produto);
//                    itemPedido.setPedido(pedido);
//                    itemPedido.setQuantidade(quantidade);
//                    return itemPedido;
//                }).collect(Collectors.toList());
//
//        return listaItens;
//
//    }

    public List<ItemPedido> converterListaItemPedidoDTOParaListaItemPedido(ProdutoService produtoService, Pedido pedido, List<ItemPedidoForm> lista) {

        List<ItemPedido> listaItens = lista.stream()
                .map(itemPedidoForm -> {

                    ItemPedido itemPedido = new ItemPedido();

                    itemPedido.setProduto(produtoService.buscarPorId(itemPedidoForm.getIdProduto()));
                    itemPedido.setPedido(pedido);
                    itemPedido.setQuantidade(itemPedidoForm.getQuantidade());
                    itemPedido.setValorPorItem(itemPedidoForm.getValorTotalItem());
                    return itemPedido;
                }).collect(Collectors.toList());

        return listaItens;

    }


}
