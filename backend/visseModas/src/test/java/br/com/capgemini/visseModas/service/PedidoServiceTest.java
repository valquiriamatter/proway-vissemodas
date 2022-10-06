package br.com.capgemini.visseModas.service;


import br.com.capgemini.visseModas.models.entities.Cliente;
import br.com.capgemini.visseModas.models.entities.Endereco;
import br.com.capgemini.visseModas.models.entities.ItemPedido;
import br.com.capgemini.visseModas.models.entities.TipoCliente;
import br.com.capgemini.visseModas.models.entities.Pedido;
import br.com.capgemini.visseModas.models.entities.Produto;
import br.com.capgemini.visseModas.models.repositories.PedidoRepository;
import org.junit.Assert;
import org.junit.Test;
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
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PedidoServiceTest {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaSalvarPedido(){
        //cria produto
        Produto produto = new Produto();
        produto.setDescricao("Shorts");
        produto.setTamanho("P");
        produto.setValorUnitario(new BigDecimal(100.00));
        produto.setCategoria("Feminino");
        em.persist(produto);

        //cria endereco
        Endereco endereco = new Endereco();
        endereco.setEstado("PR");
        endereco.setCidade("Guaira");
        endereco.setCep("85879-000");
        endereco.setLogradouro("Av Principal");
        endereco.setNumero(100);
        em.persist(endereco);

        //cria o cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Valquiria");
        cliente.setCpf("010.551.769-00");
        cliente.setEndereco(endereco);
        cliente.setTipoCliente(TipoCliente.FISICA);
        em.persist(cliente);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setValorTotal(new BigDecimal(300));
        pedido.setQuantidadeTotal(3);

        ItemPedido itemPedido1 = new ItemPedido();
        itemPedido1.setProduto(produto);
        itemPedido1.setQuantidade(3);
        itemPedido1.setValorPorItem(new BigDecimal(300));

        ItemPedido itemPedido2 = new ItemPedido();
        itemPedido2.setProduto(produto);
        itemPedido2.setQuantidade(2);
        itemPedido2.setValorPorItem(new BigDecimal(200));

        em.persist(itemPedido1);
        em.persist(itemPedido2);

        List<ItemPedido> listaItens = new ArrayList<>();
        listaItens.add(itemPedido1);
        listaItens.add(itemPedido2);

        pedido.setListaItens(listaItens);

        Pedido pedido1 = em.persist(pedido);

        Assert.assertEquals(pedido,pedido1);
    }

    @Test
    public void deveriaRetornarPedidoPorId(){
        //cria produto
        Produto produto = new Produto();
        produto.setDescricao("Shorts");
        produto.setTamanho("P");
        produto.setValorUnitario(new BigDecimal(100.00));
        produto.setCategoria("Feminino");
        em.persist(produto);

        //cria endereco
        Endereco endereco = new Endereco();
        endereco.setEstado("PR");
        endereco.setCidade("Guaira");
        endereco.setCep("85879-000");
        endereco.setLogradouro("Av Principal");
        endereco.setNumero(100);
        em.persist(endereco);

        //cria o cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Valquiria");
        cliente.setCpf("010.551.769-00");
        cliente.setEndereco(endereco);
        cliente.setTipoCliente(TipoCliente.FISICA);
        em.persist(cliente);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setValorTotal(new BigDecimal(300));
        pedido.setQuantidadeTotal(3);

        ItemPedido itemPedido1 = new ItemPedido();
        itemPedido1.setProduto(produto);
        itemPedido1.setQuantidade(3);
        itemPedido1.setValorPorItem(new BigDecimal(300));

        ItemPedido itemPedido2 = new ItemPedido();
        itemPedido2.setProduto(produto);
        itemPedido2.setQuantidade(2);
        itemPedido2.setValorPorItem(new BigDecimal(200));

        em.persist(itemPedido1);
        em.persist(itemPedido2);

        List<ItemPedido> listaItens = new ArrayList<>();
        listaItens.add(itemPedido1);
        listaItens.add(itemPedido2);

        pedido.setListaItens(listaItens);

        Pedido pedidoSalvo = em.persist(pedido);
        Pedido pedido1 = pedidoRepository.getById(pedidoSalvo.getId());

        Assert.assertEquals(pedido,pedido1);
    }

    @Test
    public void naoDeveriaRetornarPedidoPorID(){
        //cria produto
        Produto produto = new Produto();
        produto.setDescricao("Shorts");
        produto.setTamanho("P");
        produto.setValorUnitario(new BigDecimal(100.00));
        produto.setCategoria("Feminino");
        em.persist(produto);

        //cria endereco
        Endereco endereco = new Endereco();
        endereco.setEstado("PR");
        endereco.setCidade("Guaira");
        endereco.setCep("85879-000");
        endereco.setLogradouro("Av Principal");
        endereco.setNumero(100);
        em.persist(endereco);

        //cria o cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Valquiria");
        cliente.setCpf("010.551.769-00");
        cliente.setEndereco(endereco);
        cliente.setTipoCliente(TipoCliente.FISICA);
        em.persist(cliente);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setValorTotal(new BigDecimal(300));
        pedido.setQuantidadeTotal(3);

        ItemPedido itemPedido1 = new ItemPedido();
        itemPedido1.setProduto(produto);
        itemPedido1.setQuantidade(3);
        itemPedido1.setValorPorItem(new BigDecimal(300));

        ItemPedido itemPedido2 = new ItemPedido();
        itemPedido2.setProduto(produto);
        itemPedido2.setQuantidade(2);
        itemPedido2.setValorPorItem(new BigDecimal(200));

        em.persist(itemPedido1);
        em.persist(itemPedido2);

        List<ItemPedido> listaItens = new ArrayList<>();
        listaItens.add(itemPedido1);
        listaItens.add(itemPedido2);

        pedido.setListaItens(listaItens);

        Pedido pedidoSalvo = em.persist(pedido);
        Long idInvalido = 500l;

        Optional<Pedido> pedidoOptional = pedidoRepository.findById(idInvalido);
        Assert.assertFalse(pedidoOptional.isPresent());

    }
}
