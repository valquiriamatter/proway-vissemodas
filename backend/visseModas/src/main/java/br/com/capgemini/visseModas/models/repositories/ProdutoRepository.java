package br.com.capgemini.visseModas.models.repositories;

import br.com.capgemini.visseModas.models.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    //buscar produto por status (ativos ou inativos)
    List<Produto> findByStatus(Boolean status);







}
