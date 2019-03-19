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
import com.puc.dsiapi.model.TipoPessoa;
import com.puc.dsiapi.repository.TipoPessoaRepository;
import com.puc.dsiapi.service.TipopessoaService;

@RestController 
@RequestMapping("/tipopessoa") 
public class TipoPessoaResource {

	@Autowired 
	private TipoPessoaRepository tipoPessoaRepository;
	
	@Autowired
	private TipopessoaService tipoPessoaService;
	
	@GetMapping //Listar
	public List<TipoPessoa> listar() {
		return tipoPessoaRepository.findAll();
	}
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping //criar
	public ResponseEntity<TipoPessoa> criar(@Valid @RequestBody TipoPessoa tipoPessoa, HttpServletResponse response) { 
		TipoPessoa tipoPessoaSalva = tipoPessoaRepository.save(tipoPessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoPessoaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoPessoaSalva);
	}
	
	@GetMapping("/{id}") //buscar 
	public ResponseEntity<TipoPessoa> buscarPelaTipoPessoa (@PathVariable Long id) { 
		TipoPessoa tipoPessoa = tipoPessoaRepository.getOne(id);
		return tipoPessoa != null ? ResponseEntity.ok(tipoPessoa) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}") //deletar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		TipoPessoa tipoPessoaExist = tipoPessoaRepository.getOne(id);
		if(tipoPessoaExist == null) {
			throw new EmptyResultDataAccessException(1);
		}
		tipoPessoaRepository.deleteById(id);
	}
	
	@PutMapping("/{id}") //atualizar
	public ResponseEntity<TipoPessoa> atualizar(@PathVariable Long id, @RequestBody TipoPessoa tipoPessoa){
		TipoPessoa tipoPessoaSalva = tipoPessoaService.atualizar(id, tipoPessoa);
		return ResponseEntity.ok(tipoPessoaSalva);
	}
}
