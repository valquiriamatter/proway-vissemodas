package br.com.capgemini.visseModas.controllers;

import br.com.capgemini.visseModas.models.dtos.response.ItemPedidoDTO;
import br.com.capgemini.visseModas.models.dtos.request_form.ItemPedidoForm;
import br.com.capgemini.visseModas.models.entities.ItemPedido;
import br.com.capgemini.visseModas.services.ItemPedidoService;
import br.com.capgemini.visseModas.services.PedidoService;
import br.com.capgemini.visseModas.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ItemPedidoDTO> salvar(@RequestBody ItemPedidoForm form, UriComponentsBuilder uriBuilder) {

        //converte o DTO para ItemPedido
        ItemPedido itemPedido = form.formToItemPedido(produtoService, itemPedidoService);
        itemPedidoService.salvar(itemPedido);

        URI uri = uriBuilder.path("/itemPedido/{id}").buildAndExpand(itemPedido.getId()).toUri();
        return ResponseEntity.created(uri).body(new ItemPedidoDTO(itemPedido));
    }

    @GetMapping("/{idPedido}")
    public List<ItemPedidoDTO> listarItensPorPedido(@PathVariable Long idPedido) {
        return itemPedidoService.listarTudoDTOPorPedido(idPedido);
    }

}


