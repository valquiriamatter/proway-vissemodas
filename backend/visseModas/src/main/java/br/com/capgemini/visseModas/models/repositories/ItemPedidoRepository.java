package br.com.capgemini.visseModas.models.repositories;

import br.com.capgemini.visseModas.models.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    //saber quais itens tem em um pedido
    List<ItemPedido>findByPedidoId(Long id);

    //saber quais itens tem produto buscado
    List<ItemPedido>findByProdutoId(Long idProduto);





}
