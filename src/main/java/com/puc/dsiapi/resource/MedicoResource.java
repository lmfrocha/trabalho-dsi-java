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
import com.puc.dsiapi.model.Medico;
import com.puc.dsiapi.repository.MedicoRepository;
import com.puc.dsiapi.service.MedicoService;

@RestController 
@RequestMapping("/medico") 
public class MedicoResource {

	@Autowired 
	private MedicoRepository medicoRepository;
	
	@Autowired
	private MedicoService medicoService;
	
	@GetMapping //Listar
	public List<Medico> listar() {
		return medicoRepository.findAll();
	}
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping //criar
	public ResponseEntity<Medico> criar(@Valid @RequestBody Medico medico, HttpServletResponse response) { 
		Medico medicoSalva = medicoRepository.save(medico);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, medicoSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(medicoSalva);
	}
	
	@GetMapping("/{id}") //buscar 
	public ResponseEntity<Medico> buscarPelaMedico (@PathVariable Long id) { 
		Medico medico = medicoRepository.getOne(id);
		return medico != null ? ResponseEntity.ok(medico) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}") //deletar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		Medico medicoExist = medicoRepository.getOne(id);
		if(medicoExist == null) {
			throw new EmptyResultDataAccessException(1);
		}
		medicoRepository.deleteById(id);
	}
	
	@PutMapping("/{id}") //atualizar
	public ResponseEntity<Medico> atualizar(@PathVariable Long id, @RequestBody Medico medico){
		Medico medicoSalva = medicoService.atualizar(id, medico);
		return ResponseEntity.ok(medicoSalva);
	}
}
