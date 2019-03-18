package com.puc.dsiapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.puc.dsiapi.model.Medico;
import com.puc.dsiapi.repository.MedicoRepository;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository; 
	
	public Medico atualizar(Long id, Medico medico) {
		Medico medicoSalva = buscarMedicoPeloCodigo(id);
		BeanUtils.copyProperties(medico, medicoSalva, "id");
		medicoRepository.save(medicoSalva);
		return medicoRepository.save(medicoSalva);
	}

	private Medico buscarMedicoPeloCodigo(Long id) {
		Medico medicoSalva = medicoRepository.getOne(id);
		if(medicoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return medicoSalva;
	}
	
	
}
