package br.com.ehmf.App.Contatos.repository.interfaces;

import org.springframework.stereotype.Repository;

import br.com.ehmf.App.Contatos.model.Contato;
import br.com.ehmf.App.Contatos.model.Pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ContatoRepositoryInterface extends JpaRepository<Contato, Long> {
	
	List<Contato> findAllByPessoa(Optional<Pessoa> findPessoa);
}
