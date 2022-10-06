package br.com.capgemini.visseModas.controllers;

import br.com.capgemini.visseModas.services.ClienteService;
import br.com.capgemini.visseModas.models.dtos.request_form.ClienteForm;
import br.com.capgemini.visseModas.models.dtos.response.ClienteDTO;
import br.com.capgemini.visseModas.models.entities.Cliente;
import br.com.capgemini.visseModas.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;
    @Autowired
    private EnderecoService enderecoService;


    @PostMapping
    public ResponseEntity<ClienteDTO> salvar(@RequestBody @Valid ClienteForm clienteForm, UriComponentsBuilder uriBuilder) {

        Cliente cliente = clienteForm.converteFormClienteParaCliente(enderecoService);
        service.salvar(cliente);

        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDTO(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> detalhar(@PathVariable Long id) {

        Cliente cliente = service.buscarPorId(id);

        if(cliente != null){
            return ResponseEntity.ok(new ClienteDTO(cliente));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<ClienteDTO> listarTudo() {
        return service.listarTudoDTO();
    }

    @GetMapping("pageable")                     //setando uma ordenacao default, se não passar parametros
    public Page<ClienteDTO> listarTudoPaginacao(@PageableDefault(sort="nome", direction = Sort.Direction.ASC, page=0, size = 6) Pageable paginacao) {
        return service.listarTudoDTOPaginacao(paginacao);
    }








}


