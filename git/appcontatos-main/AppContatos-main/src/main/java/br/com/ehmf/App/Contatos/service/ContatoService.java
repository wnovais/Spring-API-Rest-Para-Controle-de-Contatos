package br.com.ehmf.App.Contatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.ehmf.App.Contatos.exception.ResourceNotFoundException;
import br.com.ehmf.App.Contatos.model.Contato;
import br.com.ehmf.App.Contatos.model.Pessoa;
import br.com.ehmf.App.Contatos.repository.interfaces.ContatoRepositoryInterface;
import br.com.ehmf.App.Contatos.repository.interfaces.PessoaRepositoryInterface;
import br.com.ehmf.App.Contatos.service.interfaces.ContatoServiceInterface;

@Repository
public class ContatoService implements ContatoServiceInterface {

	private ContatoRepositoryInterface contatoRepositoryInterface;
	
	@Autowired
	private PessoaRepositoryInterface pessoaRepositoryInterface;
	
	@Autowired
	public ContatoService (ContatoRepositoryInterface contatoServiceInterface)
	{
		this.contatoRepositoryInterface = contatoServiceInterface;
	}
	
	@Override
	public Contato save(Contato contato) {

		if(contato.getPessoa().getId() != null) {
			Optional<Pessoa> findPessoa = pessoaRepositoryInterface.findById(contato.getPessoa().getId());
			if(findPessoa.isEmpty()) {

				throw new ResourceNotFoundException("[Contato] A Pessoa informada não foi encontrada "
						+ "para cadastrar o Contato.");
	
			}else {
				contato.setPessoa(findPessoa.get());
				return contatoRepositoryInterface.save(contato);
			}
		}else {
			
			throw new ResourceNotFoundException("[Contato] A Pessoa informada não foi encontrada "
					+ "para cadastrar o Contato.");
		}
	}

	@Override
	public Optional<Contato> getById(Long id) {

		return contatoRepositoryInterface.findById(id);
	}

	@Override
	public List<Contato> getAllByPessoa(Long idPessoa) {

		if(idPessoa != 0) {
			Optional<Pessoa> findPessoa = pessoaRepositoryInterface.findById(idPessoa);
			if(findPessoa.isEmpty()) {
				throw new ResourceNotFoundException("[Contato] A Pessoa informada não foi encontrada "
						+ "para retornar a lista de Contatos.");
			}else {

				return contatoRepositoryInterface.findAllByPessoa(findPessoa);
			}
		}else {
			throw new ResourceNotFoundException("[Contato] A Pessoa informada não foi encontrada "
					+ "para retornar a lista de Contatos.");
		}
	}

	@Override
	public Contato update(Contato contato) {

		Optional<Contato> findContato = contatoRepositoryInterface.findById(contato.getId());
		if(findContato.isPresent()) {
			Contato updateContato = findContato.get(); //setId
			updateContato.setTipoContato(contato.getTipoContato());
			updateContato.setPessoa(contato.getPessoa());
			return contatoRepositoryInterface.save(updateContato);
		}
		return contato;
	}

	@Override
	public void delete(Long id) {

		contatoRepositoryInterface.deleteById(id);
	}
}
