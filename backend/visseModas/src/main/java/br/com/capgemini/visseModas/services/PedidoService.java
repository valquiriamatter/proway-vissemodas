package br.com.capgemini.visseModas.services;

import br.com.capgemini.visseModas.models.dtos.response.PedidoDTO;
import br.com.capgemini.visseModas.models.entities.ItemPedido;
import br.com.capgemini.visseModas.models.entities.Pedido;
import br.com.capgemini.visseModas.models.entities.Situacao;
import br.com.capgemini.visseModas.models.repositories.ItemPedidoRepository;
import br.com.capgemini.visseModas.models.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido salvar(Pedido pedido){
        pedido.setSituacao(Situacao.FECHADO);
        return pedidoRepository.save(pedido);
    }

    public Pedido detalhar(Long id){

        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            return pedidoOptional.get();
        }

        return null;
    }

    public List<PedidoDTO> listarTudoDTO(){
        List<Pedido> listaPedidos = pedidoRepository.findAll();
        return PedidoDTO.converteListaPedidoParaListaPedidoDTO(listaPedidos);
    }

    public Page<PedidoDTO> listarTudoDTOPaginacao(Pageable paginacao) {
        Page<Pedido> listaPedidos = pedidoRepository.findAll(paginacao);
        return PedidoDTO.converteListaPedidoParaListaPedidoDTOPaginacao(listaPedidos);
    }

    //add itens na lista de pedidos
    public List<ItemPedido> adicionarItem(Pedido pedido, ItemPedido item){

        if(item.getProduto().getStatus() == true){
            item.setPedido(pedido);
            item.getPedido().getListaItens().add(item);
            item.getPedido().getValorTotal().add(item.getValorPorItem());
        }

        return pedido.getListaItens();
    }


    //remove itens da lista de pedidos
    public List<ItemPedido> removerItem(ItemPedido item){
        item.getPedido().getValorTotal().subtract(item.getValorPorItem());
        item.getPedido().getListaItens().remove(item);

        return item.getPedido().getListaItens();
    }


    //calcula desconto
    public BigDecimal calcularDesconto(Pedido pedido, BigDecimal percentualDesconto){
        if(pedido.getSituacao().equals("ABERTO")) {
            BigDecimal valorFinal = pedido.getValorTotal().multiply(percentualDesconto);
            return valorFinal;
        }
        return (new BigDecimal(0));
    }

}