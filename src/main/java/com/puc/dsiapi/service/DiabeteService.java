package com.puc.dsiapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.puc.dsiapi.model.Diabete;
import com.puc.dsiapi.repository.DiabeteRepository;

@Service
public class DiabeteService {
	
	@Autowired
	private DiabeteRepository diabeteRepository; 
	
	public Diabete atualizar(Long id, Diabete diabete) {
		Diabete diabeteSalva = buscarDiabetePeloCodigo(id);
		BeanUtils.copyProperties(diabete, diabeteSalva, "id");
		diabeteRepository.save(diabeteSalva);
		return diabeteRepository.save(diabeteSalva);
	}

	private Diabete buscarDiabetePeloCodigo(Long id) {
		Diabete diabeteSalva = diabeteRepository.getOne(id);
		if(diabeteSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return diabeteSalva;
	}
	
	
}
