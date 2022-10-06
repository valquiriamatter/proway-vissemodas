package br.com.capgemini.visseModas.services;

import br.com.capgemini.visseModas.models.dtos.response.EnderecoDTO;
import br.com.capgemini.visseModas.models.entities.Endereco;
import br.com.capgemini.visseModas.models.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco alterar(Long id, EnderecoDTO dto) {

        Optional<Endereco> optional = enderecoRepository.findById(id);

        if (optional.isPresent()) {
            Endereco endereco = dto.atualizarEndereco(id, enderecoRepository);
            enderecoRepository.save(endereco);
            return endereco;
        }

        return null;
    }

    public Endereco buscarPorId(Long id) {
        Optional<Endereco> optional = enderecoRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public List<EnderecoDTO> listarTudoDTO() {
        List<Endereco> listaEndereco = enderecoRepository.findAll();
        return EnderecoDTO.converteListaEnderecoParaListaEnderecoDTO(listaEndereco);
    }

}
