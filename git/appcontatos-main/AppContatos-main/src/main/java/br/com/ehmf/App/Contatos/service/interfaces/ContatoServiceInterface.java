package br.com.ehmf.App.Contatos.service.interfaces;

import java.util.List;

import br.com.ehmf.App.Contatos.model.Contato;

import java.util.Optional;

public interface ContatoServiceInterface {

	Contato save(Contato contato);
	Optional<Contato> getById(Long id);
	List<Contato> getAllByPessoa(Long idPessoa);
	Contato update(Contato contato);
	void delete(Long id);
}
