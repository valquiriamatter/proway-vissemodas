package br.com.capgemini.visseModas.service;

import br.com.capgemini.visseModas.models.entities.Cliente;
import br.com.capgemini.visseModas.models.entities.Endereco;
import br.com.capgemini.visseModas.models.entities.TipoCliente;
import br.com.capgemini.visseModas.models.repositories.ClienteRepository;
import br.com.capgemini.visseModas.services.ClienteService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ClienteServiceTest {

    ClienteService clienteService;
    @MockBean
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setup() {
        //this.clienteService = new ClienteServiceImpl(clienteRepository);
    }

    @Test
    @DisplayName("Deve salvar cliente")
    public void salvar() {

        //cenario
        Cliente cliente = criarCliente();
        Mockito.when(clienteRepository.save(cliente))
                .thenReturn(criarCliente());

        //execucao
        Cliente clienteSalvo = clienteService.salvar(cliente);

        //verificacao
        assertThat(clienteSalvo.getId()).isNotNull();
        assertThat(clienteSalvo.getNome()).isEqualTo("Valquiria");
        assertThat(clienteSalvo.getTipoCliente()).isEqualTo(TipoCliente.FISICA);
        assertThat(clienteSalvo.getCpf()).isEqualTo("010.551.769-00");

    }


    @Test
    @DisplayName("Deve retornar um cliente pelo id informado")
    public void findById() {
        //cenario
        Long id = 1L;
        Cliente cliente = criarCliente();
        cliente.setId(id);

        Mockito.when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        //execucao
        Cliente clienteEncontrado = clienteService.buscarPorId(id);

        //verificaçao
//        assertThat(clienteEncontrado.getId().isEqualTo(id);
//        assertThat(clienteEncontrado.get().getIsbn()).isEqualTo(livro.getIsbn());
//        assertThat(clienteEncontrado.get().getTitulo()).isEqualTo(livro.getTitulo());
//        assertThat(clienteEncontrado.get().getAutor()).isEqualTo(livro.getAutor());
    }

//
//    @Test
//    public void deveriaSalvarCliente() {
//
//        Cliente cliente = new Cliente();
//        cliente.setNome("Valquiria");
//
//        Cliente c1 = em.persist(cliente);
//        Cliente c2 = clienteRepository.save(cliente);
//
//        Assert.assertEquals(cliente, c1);
//        Assert.assertEquals(cliente, c2);
//    }
//
//    @Test
//    public void deveriaRetornarClientePorId() {
//
//        Cliente cliente = new Cliente();
//        cliente.setNome("Valquiria");
//
//        Cliente clienteSalvo = em.persist(cliente);
//
//        Cliente c1 = clienteRepository.getById(clienteSalvo.getId());
//
//        Assert.assertEquals(cliente.getNome(), c1.getNome());
//
//    }
//
//    @Test
//    public void naoDeveriaRetornarClientePorId() {
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

    private Cliente criarCliente() {

        //cria endereco
        Endereco endereco = new Endereco();
        endereco.setEstado("PR");
        endereco.setCidade("Guaira");
        endereco.setCep("85879-000");
        endereco.setLogradouro("Av Principal");
        endereco.setNumero(100);
        //entityManager.persist(endereco);

        //cria o cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Valquiria");
        cliente.setCpf("010.551.769-00");
        cliente.setEndereco(endereco);
        cliente.setTipoCliente(TipoCliente.FISICA);

        return cliente;

    }
}




