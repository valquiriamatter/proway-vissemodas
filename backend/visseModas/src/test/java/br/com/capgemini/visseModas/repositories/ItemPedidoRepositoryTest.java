package br.com.capgemini.visseModas.repositories;

import br.com.capgemini.visseModas.models.entities.*;
import br.com.capgemini.visseModas.models.repositories.ItemPedidoRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ItemPedidoRepositoryTest {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deve salvar um item de pedido")
    public void salvar() {

        //cenario
        ItemPedido itemPedido = criaItemPedido();
        //execucao
        ItemPedido itemPedidoSalvo = itemPedidoRepository.save(itemPedido);
        //verificacao
        Assertions.assertThat(itemPedidoSalvo.getId()).isNotNull();
    }

    private ItemPedido criaItemPedido(){

        //cria produto
        Produto produto = new Produto();
        produto.setDescricao("Shorts");
        produto.setTamanho("P");
        produto.setValorUnitario(new BigDecimal(100.00));
        produto.setCategoria("Feminino");
        entityManager.persist(produto);

        ItemPedido itemPedido1 = new ItemPedido();
        itemPedido1.setProduto(produto);
        itemPedido1.setQuantidade(3);
        itemPedido1.setValorPorItem(new BigDecimal(300));

        return itemPedido1;

    }


}


