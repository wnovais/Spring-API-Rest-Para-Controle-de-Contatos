package br.com.ehmf.App.Contatos.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.ehmf.App.Contatos.dto.MalaDiretaDTO;
import br.com.ehmf.App.Contatos.model.Pessoa;

public interface PessoaServiceInterface {

	Pessoa save(Pessoa pessoa);
	Optional<Pessoa> getById(Long id);
	List<Pessoa> getAll();
	Optional<MalaDiretaDTO> getMalaDireta(Long id);
	Pessoa update(Pessoa pessoa);
	void delete(Long id);
}
