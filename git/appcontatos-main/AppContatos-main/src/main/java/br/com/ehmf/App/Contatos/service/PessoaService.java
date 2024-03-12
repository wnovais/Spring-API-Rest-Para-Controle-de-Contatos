package br.com.ehmf.App.Contatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.ehmf.App.Contatos.dto.MalaDiretaDTO;
import br.com.ehmf.App.Contatos.exception.ResourceNotFoundException;
import br.com.ehmf.App.Contatos.model.Pessoa;
import br.com.ehmf.App.Contatos.repository.interfaces.PessoaRepositoryInterface;
import br.com.ehmf.App.Contatos.service.interfaces.PessoaServiceInterface;

@Repository
public class PessoaService implements PessoaServiceInterface {
	
	private PessoaRepositoryInterface pessoaRepositoryInterface;
	
	@Autowired
	public PessoaService(PessoaRepositoryInterface pessoaRepositoryInterface)
	{
		this.pessoaRepositoryInterface = pessoaRepositoryInterface;
	}
	
	@Override
	public Pessoa save(Pessoa pessoa) {

		return this.pessoaRepositoryInterface.save(pessoa);
	}

	@Override
	public Optional<Pessoa> getById(Long id) {

		return this.pessoaRepositoryInterface.findById(id);
	}

	@Override
	public List<Pessoa> getAll() {
		return this.pessoaRepositoryInterface.findAll();
	}
	
	public Optional<MalaDiretaDTO> getMalaDireta(Long id) {
	
		if(this.pessoaRepositoryInterface.findById(id).isPresent())
		{
			Optional<Pessoa> pessoa = pessoaRepositoryInterface.findMalaDireta(id);
		
			if(pessoa.isPresent())
			{
				MalaDiretaDTO malaDiretaDTO = new MalaDiretaDTO(pessoa.get());
				return Optional.ofNullable(malaDiretaDTO);
			}
			else
				throw new ResourceNotFoundException("[Pessoa] A Pessoa informada não foi encontrada "
						+ "para buscar a mala direta.");
		}
		else
		{
			throw new ResourceNotFoundException("[Pessoa] A Pessoa informada não foi encontrada "
					+ "para buscar a mala direta.");
		}
	}

	@Override
	public Pessoa update(Pessoa pessoa) {
		
		Optional<Pessoa> pessoaFinded = this.pessoaRepositoryInterface.findById(pessoa.getId());
		
		if(pessoaFinded.isPresent())
		{
			Pessoa pessoaUpdated = pessoaFinded.get();
			pessoaUpdated.setCep(pessoa.getCep());
			pessoaUpdated.setCidade(pessoa.getCidade());
			pessoaUpdated.setEndereco(pessoa.getEndereco());
			pessoaUpdated.setNome(pessoa.getNome());
			pessoaUpdated.setUf(pessoa.getUf());
	
			return this.pessoaRepositoryInterface.save(pessoaUpdated);
		}
		else
		{
			return pessoa;
		}
	}

	@Override
	public void delete(Long id) {
		this.pessoaRepositoryInterface.deleteById(id);
	}

}
