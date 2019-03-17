package com.puc.dsiapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.puc.dsiapi.model.Pessoa;
import com.puc.dsiapi.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository; 
	
	public Pessoa atualizar(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(id);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		pessoaRepository.save(pessoaSalva);
		return pessoaRepository.save(pessoaSalva);
	}

	private Pessoa buscarPessoaPeloCodigo(Long id) {
		Pessoa pessoaSalva = pessoaRepository.getOne(id);
		if(pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}
	
	
}
