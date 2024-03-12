package br.com.ehmf.App.Contatos.resource;

import br.com.ehmf.App.Contatos.dto.MalaDiretaDTO;
import br.com.ehmf.App.Contatos.model.Pessoa;
import br.com.ehmf.App.Contatos.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {
	
	private PessoaService pessoaService;
	
	@Autowired 
	public PessoaResource(PessoaService pessoaService)
	{
		this.pessoaService = pessoaService;
	}

	@Operation(summary = "Busca todos as pessoas")
	@GetMapping
	public ResponseEntity<List<Pessoa>> getAllPessoas()
	{
		List<Pessoa> pessoa = pessoaService.getAll();
		if(pessoa == null)
			return ResponseEntity.notFound().build();
		if(pessoa.size() == 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoa);
	}
	
	@Operation(summary = "Cadastra pessoa")
	@PostMapping
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
		Pessoa newPessoa = pessoaService.save(pessoa);
		if(newPessoa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(newPessoa);
	}
	
	@Operation(summary = "Busca pessoa por Id")
	@GetMapping("/{id}") //http://localhost:8081/api/pessoas/2
	public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Long id){
		Optional<Pessoa> pessoa = pessoaService.getById(id);
		if(pessoa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoa);
	}
	
	@Operation(summary = "Busca mala direta por id")
	@GetMapping("/maladireta/{id}") //http://localhost:8081/api/pessoas/maladireta/{id}
	public ResponseEntity<Optional<MalaDiretaDTO>> getMalaDiretaById(@PathVariable Long id){
		Optional<MalaDiretaDTO> malaDireta =  pessoaService.getMalaDireta(id);
		
		if(!malaDireta.isPresent())
			return ResponseEntity.notFound().build();
		else
		{
			return ResponseEntity.ok(malaDireta);
		}
	}
	
	@Operation(summary = "Atualiza pessoa")
	@PutMapping
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa){
		Pessoa upPessoa = pessoaService.update(pessoa);
		if(upPessoa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(upPessoa);
	}
	
	@Operation(summary = "Exclui pessoa")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		pessoaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //status code 204
	}

}
