package br.com.capgemini.visseModas.models.dtos.response;

import br.com.capgemini.visseModas.models.entities.Endereco;
import br.com.capgemini.visseModas.models.repositories.EnderecoRepository;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EnderecoDTO {

    private Long id;
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private Integer numero;

    public EnderecoDTO() {
    }

    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.cep = endereco.getCep();
        this.estado = endereco.getEstado();
        this.cidade = endereco.getCidade();
        this.bairro = endereco.getBairro();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
    }

    public static List<EnderecoDTO> converteListaEnderecoParaListaEnderecoDTO(List<Endereco> listaEnderecos) {
        return listaEnderecos.stream().map(EnderecoDTO::new).collect(Collectors.toList());
    }

    public Endereco converteEnderecoDTOParaEndereco() {

        Endereco endereco = new Endereco();

        endereco.setId(id);
        endereco.setCep(cep);
        endereco.setEstado(estado);
        endereco.setCidade(cidade);
        endereco.setBairro(bairro);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);

        return endereco;
    }


    public Endereco atualizarEndereco(Long id, EnderecoRepository enderecoRepository) {

        Endereco endereco = enderecoRepository.getById(id);

        endereco.setId(id);
        endereco.setCep(cep);
        endereco.setEstado(estado);
        endereco.setCidade(cidade);
        endereco.setBairro(bairro);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);

        return endereco;
    }

}
