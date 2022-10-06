package br.com.capgemini.visseModas.services;

import br.com.capgemini.visseModas.models.dtos.response.ProdutoDTO;
import br.com.capgemini.visseModas.models.entities.ItemPedido;
import br.com.capgemini.visseModas.models.entities.Produto;
import br.com.capgemini.visseModas.models.repositories.ItemPedidoRepository;
import br.com.capgemini.visseModas.models.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Produto salvar(Produto produto){
       return produtoRepository.save(produto);
    }

    public Produto alterar(Long id, ProdutoDTO produtoDTO) {

        Optional<Produto> optional =  produtoRepository.findById(id);
        if (optional.isPresent()) {

            Produto produto = produtoDTO.atualizarProduto(id, produtoRepository);
            produtoRepository.save(produto);
            return produto;
        }
        return null;
    }

    //Não deve ser possível excluir um produto se ele estiver associado a algum pedido
    public Boolean deletar(Long idProduto) {

        //busca o produto pelo id
        Optional<Produto> optional = produtoRepository.findById(idProduto);
        Produto produto = optional.get();

        //verifica se o produto está vinculado a algum item
        List<ItemPedido> lista = itemPedidoRepository.findByProdutoId(idProduto);

        //se não tiver vínculo a lista volta vazia e pode exluir o produto
        if(lista.isEmpty()){
            produtoRepository.delete(produto);
            return true;
        }

        //se a lista não tiver vazia, não pode excluir portanto o produto será inativado
        inativar(produto);
        return false;
    }

    public Produto buscarPorId(Long id) {

        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            return produtoOptional.get();
        }

        return null;

    }

    public List<ProdutoDTO> listarTudo(){
        List<Produto> listaProdutos = produtoRepository.findAll();
        return ProdutoDTO.converteListaProdutoParaListaProdutoDTO(listaProdutos);
    }

    public List<ProdutoDTO> listarTudoAtivo(){
        List<Produto> listaProdutos = produtoRepository.findByStatus(true);
        return ProdutoDTO.converteListaProdutoParaListaProdutoDTO(listaProdutos);
    }

    public Page<ProdutoDTO> listarTudoDTOPaginacao(Pageable paginacao) {
        Page<Produto> listaProdutos = produtoRepository.findAll(paginacao);
        return ProdutoDTO.converteListaProdutoParaListaProdutoDTOPaginacao(listaProdutos);
    }

    //metodo para inativar produto
    public void inativar(Produto produto){
        produto.setStatus(false);
        produtoRepository.save(produto);
    }




}
