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
import com.puc.dsiapi.model.Glicemia;
import com.puc.dsiapi.repository.GlicemiaRepository;
import com.puc.dsiapi.service.GlicemiaService;

@RestController 
@RequestMapping("/glicemia") 
public class GlicemiaResource {

	@Autowired 
	private GlicemiaRepository glicemiaRepository;
	
	@Autowired
	private GlicemiaService glicemiaService;
	
	@GetMapping //Listar
	public List<Glicemia> listar() {
		return glicemiaRepository.findAll();
	}
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping //criar
	public ResponseEntity<Glicemia> criar(@RequestBody Glicemia glicemia, HttpServletResponse response) { 
		Glicemia glicemiaSalva = glicemiaRepository.save(glicemia);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, glicemiaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(glicemiaSalva);
	}
	
	@GetMapping("/{id}") //buscar 
	public ResponseEntity<Glicemia> buscarPelaGlicemia (@PathVariable Long id) { 
		Glicemia glicemia = glicemiaRepository.getOne(id);
		return glicemia != null ? ResponseEntity.ok(glicemia) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}") //deletar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		Glicemia glicemiaExist = glicemiaRepository.getOne(id);
		if(glicemiaExist == null) {
			throw new EmptyResultDataAccessException(1);
		}
		glicemiaRepository.deleteById(id);
	}
	
	@PutMapping("/{id}") //atualizar
	public ResponseEntity<Glicemia> atualizar(@PathVariable Long id, @RequestBody Glicemia glicemia){
		Glicemia glicemiaSalva = glicemiaService.atualizar(id, glicemia);
		return ResponseEntity.ok(glicemiaSalva);
	}
}
