package com.puc.dsiapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.puc.dsiapi.model.Glicemia;
import com.puc.dsiapi.repository.GlicemiaRepository;

@Service
public class GlicemiaService {
	
	@Autowired
	private GlicemiaRepository glicemiaRepository; 
	
	public Glicemia atualizar(Long id, Glicemia glicemia) {
		Glicemia glicemiaSalva = buscarGlicemiaPeloCodigo(id);
		BeanUtils.copyProperties(glicemia, glicemiaSalva, "id");
		glicemiaRepository.save(glicemiaSalva);
		return glicemiaRepository.save(glicemiaSalva);
	}

	private Glicemia buscarGlicemiaPeloCodigo(Long id) {
		Glicemia glicemiaSalva = glicemiaRepository.getOne(id);
		if(glicemiaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return glicemiaSalva;
	}
	
	
}
