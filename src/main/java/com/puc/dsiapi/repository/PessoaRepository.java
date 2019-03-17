package com.puc.dsiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puc.dsiapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
