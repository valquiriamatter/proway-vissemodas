package br.com.capgemini.visseModas.models.repositories;

import br.com.capgemini.visseModas.models.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
