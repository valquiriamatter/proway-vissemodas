package br.com.capgemini.visseModas.models.dtos.request_form;


import br.com.capgemini.visseModas.models.entities.Produto;
import br.com.capgemini.visseModas.models.repositories.ProdutoRepository;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ProdutoForm {

    @NotBlank(message = "O campo Descrição é obrigatório")
    private String descricao;
    @NotBlank(message = "O campo Tamanho é obrigatório")
    private String tamanho;
    @DecimalMax("1000.00")
    private BigDecimal valorUnitario;
    @NotBlank(message = "O campo Categoria é obrigatório")
    private String categoria;

    private String imagem;


    public ProdutoForm(){

    }

    public Produto converteFormParaProduto() {

        Produto produto = new Produto();

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



    }

