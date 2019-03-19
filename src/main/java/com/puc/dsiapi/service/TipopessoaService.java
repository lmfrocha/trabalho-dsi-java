package com.puc.dsiapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.puc.dsiapi.model.TipoPessoa;
import com.puc.dsiapi.repository.TipoPessoaRepository;

@Service
public class TipopessoaService {
	
	@Autowired
	private TipoPessoaRepository tipoPessoaRepository; 
	
	public TipoPessoa atualizar(Long id, TipoPessoa tipoPessoa) {
		TipoPessoa tipoPessoaSalva = buscarTipoPessoaPeloCodigo(id);
		BeanUtils.copyProperties(tipoPessoa, tipoPessoaSalva, "id");
		tipoPessoaRepository.save(tipoPessoaSalva);
		return tipoPessoaRepository.save(tipoPessoaSalva);
	}

	private TipoPessoa buscarTipoPessoaPeloCodigo(Long id) {
		TipoPessoa tipoPessoaSalva = tipoPessoaRepository.getOne(id);
		if(tipoPessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return tipoPessoaSalva;
	}
	
	
}
