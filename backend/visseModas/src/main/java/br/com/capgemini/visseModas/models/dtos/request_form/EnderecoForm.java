package br.com.capgemini.visseModas.models.dtos.request_form;

import br.com.capgemini.visseModas.models.entities.Endereco;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
public class EnderecoForm {

    @NotBlank(message = "O campo CEP é obrigatório")
    private String cep;
    @NotBlank(message = "O campo Estado é obrigatório")
    private String estado;
    @NotBlank(message = "O campo Cidade é obrigatório")
    private String cidade;
    @NotBlank(message = "O campo Bairro é obrigatório")
    private String bairro;
    @NotBlank(message = "O campo Logradouro é obrigatório")
    private String logradouro;

    private Integer numero=0;

    public EnderecoForm() {
    }

    public Endereco converteEnderecoDTOParaEndereco() {

        Endereco endereco = new Endereco();

        endereco.setCep(this.cep);
        endereco.setEstado(this.estado);
        endereco.setCidade(this.cidade);
        endereco.setBairro(this.bairro);
        endereco.setLogradouro(this.logradouro);
        endereco.setNumero(this.numero);

        return endereco;
    }


}
