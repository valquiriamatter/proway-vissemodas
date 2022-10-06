package br.com.capgemini.visseModas.services;

import br.com.capgemini.visseModas.models.dtos.response.ItemPedidoDTO;
import br.com.capgemini.visseModas.models.entities.ItemPedido;
import br.com.capgemini.visseModas.models.entities.Produto;
import br.com.capgemini.visseModas.models.repositories.ItemPedidoRepository;
import br.com.capgemini.visseModas.models.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
    }

    public ItemPedido salvar(ItemPedido itemPedido){
        return itemPedidoRepository.save(itemPedido);
    }


    //atualiza valor do item
    public BigDecimal calcularValorPorItem(ItemPedido itemPedido){

        //pego qual é o produto
        Produto produto = produtoRepository.getById(itemPedido.getProduto().getId());
        //atribui ao valor total dos itens (valor produto * quantidade)
        itemPedido.setValorPorItem(produto.getValorUnitario().multiply(new BigDecimal(itemPedido.getQuantidade())));

        return itemPedido.getValorPorItem();
    }


    public List<ItemPedidoDTO> listarTudoDTOPorPedido(Long idPedido){
        List<ItemPedido> listaItemPedido = itemPedidoRepository.findByPedidoId(idPedido);
        return ItemPedidoDTO.converteListaItemPedidoParaListaItemPedidoDTO(listaItemPedido);
    }

}