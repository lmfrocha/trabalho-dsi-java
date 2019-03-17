package com.puc.dsiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puc.dsiapi.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
