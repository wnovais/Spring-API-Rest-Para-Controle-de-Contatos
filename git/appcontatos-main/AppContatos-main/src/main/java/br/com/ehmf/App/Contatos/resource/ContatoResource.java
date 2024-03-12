package br.com.ehmf.App.Contatos.resource;

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

import br.com.ehmf.App.Contatos.model.Contato;
import br.com.ehmf.App.Contatos.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/contato")
public class ContatoResource {
	
	private ContatoService contatoService;

	@Autowired 
	public ContatoResource(ContatoService contatoService) {
		this.contatoService = contatoService;
	}
	
	@Operation(summary = "Cadastra contato")
	@PostMapping
	public ResponseEntity<Contato> save(@RequestBody Contato contato){
		Contato newContato = contatoService.save(contato);
		if(newContato == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(newContato);
	}
	
	@Operation(summary = "Busca contato por Id")
	@GetMapping("/{id}") //http://localhost:8080/api/contato/2
	public ResponseEntity<Optional<Contato>> getById(@PathVariable Long id){
		Optional<Contato> contato = contatoService.getById(id);
		if(contato == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(contato);
	}
	
	@Operation(summary = "Busca todos os contatos")
	@GetMapping("{idPessoa}/contatos") //http://localhost:8080/api/contato/1/contatos
	public ResponseEntity<List<Contato>> getAllByPessoa(@PathVariable Long idPessoa){
		List<Contato> contato = contatoService.getAllByPessoa(idPessoa);
		if(contato == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(contato);
	}
	
	@Operation(summary = "Atualiza contato")
	@PutMapping
	public ResponseEntity<Contato> update(@RequestBody Contato contato){
		Contato upContato = contatoService.update(contato);
		if(upContato == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(upContato);
	}
	
	@Operation(summary = "Exclui contato")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		contatoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //status code 204
	}

}
