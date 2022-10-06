package br.com.capgemini.visseModas.service;


import br.com.capgemini.visseModas.models.entities.Endereco;
import br.com.capgemini.visseModas.models.repositories.EnderecoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class EnderecoServiceTest {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private TestEntityManager em;


    @Test
    public void deveriaSalvarEndereco(){
        Endereco endereco = new Endereco();
        endereco.setBairro("Ipiranga");
        endereco.setCidade("Rio de Janeiro");
        endereco.setEstado("RJ");
        endereco.setLogradouro("Avenida Garota de Ipanema");
        endereco.setNumero(10);
        endereco.setCep("76254-852");

        Endereco ed1 = em.persist(endereco);

        Assert.assertEquals(endereco,ed1);
    }

    @Test
    public void deveriaRetornarEnderecoPorId(){

        Endereco endereco = new Endereco();
        endereco.setBairro("Ipiranga");
        endereco.setCidade("Rio de Janeiro");
        endereco.setEstado("RJ");
        endereco.setLogradouro("Avenida Garota de Ipanema");
        endereco.setNumero(10);
        endereco.setCep("76254-852");

        Endereco enderecoSalvo = em.persist(endereco);

        Endereco end1 = enderecoRepository.getById(enderecoSalvo.getId());

        Assert.assertEquals(endereco,end1);


    }

    @Test
    public void naoDeveriaRetornarEnderecoPorId(){

        Endereco endereco = new Endereco();
        endereco.setBairro("Ipiranga");
        Endereco enderecoSalvo = em.persist(endereco);
        Long idInvalido=25l;

        Optional<Endereco> enderecoOptional = enderecoRepository.findById(idInvalido);
        Assert.assertFalse(enderecoOptional.isPresent());


    }

}
