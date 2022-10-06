package br.com.capgemini.visseModas.controllers;

import br.com.capgemini.visseModas.services.EnderecoService;
import br.com.capgemini.visseModas.models.dtos.request_form.EnderecoForm;
import br.com.capgemini.visseModas.models.dtos.response.EnderecoDTO;
import br.com.capgemini.visseModas.models.entities.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping
    public ResponseEntity<EnderecoDTO> salvar(@RequestBody @Valid EnderecoForm enderecoForm, UriComponentsBuilder uriBuilder) {

        Endereco endereco = enderecoForm.converteEnderecoDTOParaEndereco();
        service.salvar(endereco);

        URI uri = uriBuilder.path("/enderecos/{id}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).body(new EnderecoDTO(endereco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> alterar(@PathVariable Long id, @RequestBody @Valid EnderecoDTO form, UriComponentsBuilder uriBuilder ) {

        Endereco endereco = form.converteEnderecoDTOParaEndereco();
        endereco = service.alterar(id, form);

        URI uri = uriBuilder.path("/enderecos/{id}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).body(new EnderecoDTO(endereco));

    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> detalhar(@PathVariable Long id) {

        Endereco endereco = service.buscarPorId(id);

        if(endereco != null){
            return ResponseEntity.ok(new EnderecoDTO(endereco));
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping
    public List<EnderecoDTO> listarTudo() {
        return service.listarTudoDTO();
    }


}


