package br.com.capgemini.visseModas.models.repositories;

import br.com.capgemini.visseModas.models.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
