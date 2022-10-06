package br.com.capgemini.visseModas.controllers;

import br.com.capgemini.visseModas.models.dtos.response.ProdutoDTO;
import br.com.capgemini.visseModas.models.dtos.request_form.ProdutoForm;
import br.com.capgemini.visseModas.services.ProdutoService;
import br.com.capgemini.visseModas.models.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {

        Produto produto = form.converteFormParaProduto();
        service.salvar(produto);

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDTO(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> alterar(@PathVariable Long id, @RequestBody @Valid ProdutoDTO produtoDTO, UriComponentsBuilder uriBuilder ) {

        Produto produto = produtoDTO.converteProdutoDTOParaProduto();
        produto = service.alterar(id, produtoDTO);

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDTO(produto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id, UriComponentsBuilder uriBuilder) {

        Boolean resposta = service.deletar(id); //chamar o deletar e decidir no service se deleta ou nao

        if(resposta == false){

            Produto produto = service.buscarPorId(id);

            URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
            return ResponseEntity.created(uri).body(new ProdutoDTO(produto));
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> detalhar(@PathVariable Long id) {

        Produto produto = service.buscarPorId(id);

        if(produto != null){
            return ResponseEntity.ok(new ProdutoDTO(produto));
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping
    public List<ProdutoDTO> listarTudoAtivo() {
        return service.listarTudoAtivo();
    }

    @GetMapping("todos")
    public List<ProdutoDTO> listarTudo() {
        return service.listarTudo();
    }

    @GetMapping("pageable")
    public Page<ProdutoDTO> listarTudoPaginacao(@PageableDefault(sort="descricao", direction = Sort.Direction.ASC, page=0, size = 6) Pageable paginacao) {
        return service.listarTudoDTOPaginacao(paginacao);
    }

}


