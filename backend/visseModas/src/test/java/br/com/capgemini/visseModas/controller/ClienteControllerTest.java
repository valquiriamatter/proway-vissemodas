package br.com.capgemini.visseModas.controller;


import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClienteControllerTest {

    private MockMvc mockMvc; //duble, simula uma requisicao, como se fosse o postman

    public void deveriaDevolver404() throws Exception {
        URI uri = new URI("/clientes");
        String json = "{\"email\"}";

        mockMvc
                .perform(MockMvcRequestBuilders  //faca uma requisicao
                        .post(uri) //tipo post para a URI
                        .content(json) //contendo o json
                        .contentType(MediaType.APPLICATION_JSON))//com o cabecalho json
                .andExpect(MockMvcResultMatchers //espera
                        .status() //como status
                        .isNotFound()); //um 404 como resposta
    }






}
