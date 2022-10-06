package br.com.capgemini.visseModas.repositories;

import br.com.capgemini.visseModas.models.entities.Produto;
import br.com.capgemini.visseModas.models.repositories.ProdutoRepository;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deve retornar somente produtos ativos.")
    public void findByStatusTrue(){

        //cenario
        Produto p1 = criarProduto1();
        Produto p2 = criarProduto2();

        //execucao
        entityManager.persist(p1);
        entityManager.persist(p2);

        List<Produto> banco = produtoRepository.findByStatus(true);
        List<String> atual = Arrays.asList(p1.getDescricao());
        List<String> listaBanco = new ArrayList<>();

        for (Produto p : banco) {
           listaBanco = Arrays.asList(p.getDescricao());
        }
        //verificacao
        assertArrayEquals(atual.toArray(), listaBanco.toArray());
    }

    @Test
    @DisplayName("Deveria retornar todos os produtos.")
    public void findAll(){

        //cenario
        Produto p1 = criarProduto1();
        Produto p2 = criarProduto2();

        //execucao
        entityManager.persist(p1);
        entityManager.persist(p2);

        List<Produto> banco = produtoRepository.findAll();

        List<String> atual = Arrays.asList(p1.getDescricao(), p2.getDescricao());
        List<String> listaBanco = new ArrayList<>(banco.size());

        for (Produto p : banco) {
            listaBanco.add(p.getDescricao());
        }
        //verificacao
        assertArrayEquals(atual.toArray(), listaBanco.toArray());
    }

    @Test
    @DisplayName("Deve obter um produto pelo id")
    public void findById() {

        //cenario
        Produto produto = criarProduto1();
        entityManager.persist(produto);

        //execucao
        Optional<Produto> produtoLocalizado = produtoRepository.findById(produto.getId());

        //verificacao
        Assertions.assertThat(produtoLocalizado.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Deve salvar um produto")
    public void salvar() {
        //cenario
        Produto produto = criarProduto1();
        //execucao
        Produto produtoSalvo = produtoRepository.save(produto);
        //verificacao
        Assertions.assertThat(produtoSalvo.getId()).isNotNull();
    }



    private Produto criarProduto1() {

        //cenario
        Produto p1 = new Produto();
        p1.setDescricao("Shorts");
        p1.setTamanho("P");
        p1.setValorUnitario(new BigDecimal(100.00));
        p1.setCategoria("Feminino");

        return p1;
    }

    private Produto criarProduto2(){

        Produto p2 = new Produto();
        p2.setDescricao("Camiseta");
        p2.setTamanho("M");
        p2.setValorUnitario(new BigDecimal(70.00));
        p2.setCategoria("Infantil");
        p2.setStatus(false);

        return p2;
    }



}
