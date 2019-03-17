package com.puc.dsiapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.puc.dsiapi.event.RecursoCriadoEvent;
import com.puc.dsiapi.model.Pessoa;
import com.puc.dsiapi.repository.PessoaRepository;
import com.puc.dsiapi.service.PessoaService;

@RestController 
@RequestMapping("/pessoa") 
public class PessoaResource {

	@Autowired 
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping //Listar
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping //criar
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) { 
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@GetMapping("/{id}") //buscar 
	public ResponseEntity<Pessoa> buscarPelaPessoa (@PathVariable Long id) { 
		Pessoa pessoa = pessoaRepository.getOne(id);
		return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}") //deletar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		Pessoa pessoaExist = pessoaRepository.getOne(id);
		if(pessoaExist == null) {
			throw new EmptyResultDataAccessException(1);
		}
		pessoaRepository.deleteById(id);
	}
	
	@PutMapping("/{id}") //atualizar
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa){
		Pessoa pessoaSalva = pessoaService.atualizar(id, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}
}
