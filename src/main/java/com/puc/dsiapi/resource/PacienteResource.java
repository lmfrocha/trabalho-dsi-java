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
import com.puc.dsiapi.model.Paciente;
import com.puc.dsiapi.repository.PacienteRepository;
import com.puc.dsiapi.service.PacienteService;

@RestController 
@RequestMapping("/paciente") 
public class PacienteResource {

	@Autowired 
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping //Listar
	public List<Paciente> listar() {
		return pacienteRepository.findAll();
	}
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping //criar
	public ResponseEntity<Paciente> criar(@Valid @RequestBody Paciente paciente, HttpServletResponse response) { 
		Paciente pacienteSalvo = pacienteRepository.save(paciente);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pacienteSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
	}
	
	@GetMapping("/{id}") //buscar 
	public ResponseEntity<Paciente> buscarPeloPaciente (@PathVariable Long id) { 
		Paciente paciente = pacienteRepository.getOne(id);
		return paciente != null ? ResponseEntity.ok(paciente) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}") //deletar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		Paciente pacienteExist = pacienteRepository.getOne(id);
		if(pacienteExist == null) {
			throw new EmptyResultDataAccessException(1);
		}
		pacienteRepository.deleteById(id);
	}
	
	@PutMapping("/{id}") //atualizar
	public ResponseEntity<Paciente> atualizar(@PathVariable Long id, @RequestBody Paciente paciente){
		Paciente pacienteSalva = pacienteService.atualizar(id, paciente);
		return ResponseEntity.ok(pacienteSalva);
	}
}
