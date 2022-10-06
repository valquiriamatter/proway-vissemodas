package br.com.capgemini.visseModas.models.dtos.request_form;

import br.com.capgemini.visseModas.models.entities.*;
import br.com.capgemini.visseModas.services.EnderecoService;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
public class ClienteForm {

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String nome;
    @CPF(message = "CPF inválido.")
    private String cpf;
    @CNPJ(message = "CNPJ inválido.")
    private String cnpj;
    @NotBlank(message = "O tipo do cliente é obrigatório.")
    private String tipoCliente;
    @NotNull(message = "O endereço não pode ser nulo.")
    private Long idEndereco;

    public ClienteForm(){

    }

    //pega os dados do DTO e cria em um cliente
    public Cliente converteFormClienteParaCliente(EnderecoService enderecoService) {

        Endereco endereco = enderecoService.buscarPorId(idEndereco);

        Cliente cliente = new Cliente();

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setCnpj(cnpj);
        cliente.setEndereco(endereco);
        cliente.setTipoCliente(TipoCliente.valueOf(tipoCliente));

        return cliente;
    }



}
