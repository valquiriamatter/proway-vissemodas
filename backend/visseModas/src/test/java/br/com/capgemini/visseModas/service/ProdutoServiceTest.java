package br.com.capgemini.visseModas.service;


import br.com.capgemini.visseModas.models.entities.Produto;
import br.com.capgemini.visseModas.models.repositories.ProdutoRepository;
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
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProdutoServiceTest {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaSalvarProduto() {

        Produto produto = new Produto();
        produto.setDescricao("Vestido Longo Turco");
        produto.setTamanho("40");
        produto.setValorUnitario(BigDecimal.valueOf(150.15));
        produto.setCategoria("Vestidos");
        produto.setImagem("C:");

        Produto prod1 = em.persist(produto);

        Assert.assertEquals(produto, prod1);

    }

    @Test
    public void deveriaBuscarProdutoPorId() {

        Produto produto = new Produto();
        produto.setDescricao("Vestido Longo Turco");
        produto.setTamanho("40");
        produto.setValorUnitario(BigDecimal.valueOf(150.15));
        produto.setCategoria("Vestidos");
        produto.setImagem("C:");

        Produto produtoSalvo = em.persist(produto);

        Produto prod1 = produtoRepository.getById(produtoSalvo.getId());

        Assert.assertEquals(produto, prod1);
    }

    @Test
    public void naoDeveriaBuscarProdutoPorId() {

        Produto produto = new Produto();
        produto.setDescricao("Calças");
        Produto produtoSalvo = em.persist(produto);
        Long idInvalido = 35l;

        Optional<Produto> produtoOptional = produtoRepository.findById(idInvalido);
        Assert.assertFalse(produtoOptional.isPresent());

    }
}

