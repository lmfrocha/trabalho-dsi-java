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
import com.puc.dsiapi.model.Diabete;
import com.puc.dsiapi.repository.DiabeteRepository;
import com.puc.dsiapi.service.DiabeteService;

@RestController 
@RequestMapping("/diabete") 
public class DiabeteResource {

	@Autowired 
	private DiabeteRepository diabeteRepository;
	
	@Autowired
	private DiabeteService diabeteService;
	
	@GetMapping //Listar
	public List<Diabete> listar() {
		return diabeteRepository.findAll();
	}
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping //criar
	public ResponseEntity<Diabete> criar(@Valid @RequestBody Diabete diabete, HttpServletResponse response) { 
		Diabete diabeteSalva = diabeteRepository.save(diabete);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, diabeteSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(diabeteSalva);
	}
	
	@GetMapping("/{id}") //buscar 
	public ResponseEntity<Diabete> buscarPelaDiabete (@PathVariable Long id) { 
		Diabete diabete = diabeteRepository.getOne(id);
		return diabete != null ? ResponseEntity.ok(diabete) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}") //deletar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		Diabete diabeteExist = diabeteRepository.getOne(id);
		if(diabeteExist == null) {
			throw new EmptyResultDataAccessException(1);
		}
		diabeteRepository.deleteById(id);
	}
	
	@PutMapping("/{id}") //atualizar
	public ResponseEntity<Diabete> atualizar(@PathVariable Long id, @RequestBody Diabete diabete){
		Diabete diabeteSalva = diabeteService.atualizar(id, diabete);
		return ResponseEntity.ok(diabeteSalva);
	}
}
