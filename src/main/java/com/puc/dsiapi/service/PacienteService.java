package com.puc.dsiapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.puc.dsiapi.model.Paciente;
import com.puc.dsiapi.repository.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository; 
	
	public Paciente atualizar(Long id, Paciente paciente) {
		Paciente pacienteSalva =  buscarPacientePeloCodigo(id);
		BeanUtils.copyProperties(paciente, pacienteSalva, "id");
		pacienteRepository.save(pacienteSalva);
		return pacienteRepository.save(pacienteSalva);
	}

	private Paciente buscarPacientePeloCodigo(Long id) {
		Paciente pacienteSalva = pacienteRepository.getOne(id);
		if(pacienteSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pacienteSalva;
	}
	
	
}
