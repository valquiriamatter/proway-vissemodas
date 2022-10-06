package br.com.capgemini.visseModas.service;

import br.com.capgemini.visseModas.models.entities.ItemPedido;
import br.com.capgemini.visseModas.models.entities.Produto;
import br.com.capgemini.visseModas.models.repositories.ItemPedidoRepository;
import br.com.capgemini.visseModas.services.ItemPedidoService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ItemPedidoServiceTest {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @InjectMocks
    private ItemPedidoService itemPedidoService;
    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaSalvarItemPedido() {

        Produto produto = new Produto();
        produto.setDescricao("Produto Teste");
        produto.setValorUnitario(new BigDecimal(20));

        produto = em.persist(produto);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(2);

        ItemPedido itemPedidoBanco = em.persist(itemPedido);

        //Assert.assertEquals(itemPedidoBanco, itemPedido);

        given(itemPedidoRepository.findById(itemPedido.getProduto().getId())).willReturn(Optional.empty());
        given(itemPedidoRepository.save(itemPedido)).willAnswer(invocation -> invocation.getArgument(0));

        ItemPedido itemService = itemPedidoService.salvar(itemPedido);
        assertThat(itemService).isNotNull();

        verify(itemPedidoRepository).save(any(ItemPedido.class));
    }
}


//        @BeforeEach
//        void setUp() {
//            itemPedidoService = new ItemPedidoService(itemPedidoRepository);
//        }
//
//        @AfterEach
//        void tearDown() {
//            clearInvocations();
//        }
//
//        @Test
//        public void createTodo() {
//            itemPedidoService.salvar(new ItemPedido());
//
//            // verify if the save method is called when createTodo is called too
//            verify(itemPedidoRepository, times(1)).save(any(ItemPedido.class));
//        }
//    }




//    @Test //ver pq a injecao produtoRepository no Service tá nula.
//    public void calcularValorPorItem() {
//
//        Produto produto = new Produto();
//        produto.setDescricao("Produto Teste");
//        produto.setValorUnitario(new BigDecimal(20));
//        produto = produtoRepository.save(produto);
//
//        ItemPedido itemPedido = new ItemPedido();
//        itemPedido.setProduto(produto);
//        itemPedido.setQuantidade(2);
//
//        ItemPedidoService itemPedidoService = new ItemPedidoService();
//
//        itemPedido.setValorPorItem(itemPedidoService.calcularValorPorItem(itemPedido));
//
//        BigDecimal precoPorItem = itemPedidoService.calcularValorPorItem(itemPedido);
//
//        Assert.assertEquals(precoPorItem, new BigDecimal(80));
//    }
//
//    @Test
//    public void adicionarItem() {
//    }
//
//    @Test
//    public void removerItem() {
//    }
//

//    @Test
//    public void deveriaRetornarItensPorIdPedido() {
//
//        Produto produto = new Produto();
//        produto.setDescricao("Produto Teste");
//        produto.setValorUnitario(new BigDecimal(20));
//        produto = em.persist(produto);
//
//        ItemPedido itemPedido = new ItemPedido();
//        itemPedido.setProduto(produto);
//        itemPedido.setQuantidade(2);
//        ItemPedido itemPedidoBanco = em.persist(itemPedido);
//
//        Pedido pedido = new Pedido();
//        pedido.setListaItens(Arrays.asList(itemPedido));
//        pedido.setValorTotal(new BigDecimal(100));
//
//        Cliente cliente = new Cliente();
//        cliente.setNome("Valquiria");
//        cliente = em.persist(cliente);
//
//        pedido.setCliente(cliente);
//        pedido = em.persist(pedido);
//
//        Long idPedido = pedido.getId();
//
//        List<ItemPedido> listaItens = itemPedidoRepository.findByPedidoId(idPedido);
//        List<ItemPedido> lista = Arrays.asList(itemPedido);
//
//        Assertions.assertArrayEquals(listaItens.toArray(), lista.toArray());
//    }

//        @TestConfiguration
//        static class ItemPedidoServiceTestContextConfiguration {
//
//            @Autowired
//            private ClienteService clienteService;
//            @Autowired
//            private ProdutoRepository produtoRepository;
//
//            @Bean
//            public ItemPedidoService employeeService() {
//                return new ItemPedidoService() {
//                };
//            }
//
//        }


//    @Test
//    public void naoDeveriaRetornarItensPorIdPedido() {
//
//        Cliente cliente = new Cliente();
//        cliente.setNome("Valquiria");
//        Cliente clienteSalvo = em.persist(cliente);
//        Long idInvalido = 50l;
//
//        Optional<Cliente> clienteOptional = clienteRepository.findById(idInvalido);
//        Assert.assertFalse(clienteOptional.isPresent());
//
//    }
