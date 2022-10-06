package br.com.capgemini.visseModas.services;

import br.com.capgemini.visseModas.models.dtos.response.ClienteDTO;
import br.com.capgemini.visseModas.models.entities.Cliente;
import br.com.capgemini.visseModas.models.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
       return clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            return clienteOptional.get();
        }

        return null;
    }


    public List<ClienteDTO> listarTudoDTO() {
        List<Cliente> listaClientes = clienteRepository.findAll();
        return ClienteDTO.converter(listaClientes);
    }

    public Page<ClienteDTO> listarTudoDTOPaginacao(Pageable paginacao) {
        Page<Cliente> listaClientes = clienteRepository.findAll(paginacao);
        return ClienteDTO.converterPaginacao(listaClientes);
    }

}