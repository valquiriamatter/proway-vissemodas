package br.com.capgemini.visseModas.models.dtos.response;

import br.com.capgemini.visseModas.models.entities.Produto;
import br.com.capgemini.visseModas.models.repositories.ProdutoRepository;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProdutoDTO {

    private Long id;
    private String descricao;
    private String tamanho;
    private BigDecimal valorUnitario;
    private String categoria;
    private String imagem;
    private Boolean status;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto){
        this.id = produto.getId();
        this.descricao = produto.getDescricao();
        this.tamanho = produto.getTamanho();
        this.valorUnitario = produto.getValorUnitario();
        this.categoria = produto.getCategoria();
        this.imagem = produto.getImagem();
        this.status = produto.getStatus();
    }

    public Produto converteProdutoDTOParaProduto() {

        Produto produto = new Produto();

        produto.setId(id);
        produto.setDescricao(descricao);
        produto.setTamanho(tamanho);
        produto.setValorUnitario(valorUnitario);
        produto.setCategoria(categoria);
        produto.setImagem(imagem);

        return produto;
    }


    public Produto atualizarProduto(Long id, ProdutoRepository produtoRepository) {

        Produto produto = produtoRepository.getById(id);

        produto.setDescricao(descricao);
        produto.setTamanho(tamanho);
        produto.setValorUnitario(valorUnitario);
        produto.setCategoria(categoria);
        produto.setImagem(imagem);

        return produto;
    }


    public static List<ProdutoDTO> converteListaProdutoParaListaProdutoDTO(List<Produto> produtos){
        return produtos.stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }

    public static Page<ProdutoDTO> converteListaProdutoParaListaProdutoDTOPaginacao(Page<Produto> listaProdutos){
        return listaProdutos.map(ProdutoDTO::new);
    }

}
