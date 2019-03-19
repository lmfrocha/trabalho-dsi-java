package com.puc.dsiapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "glicemia")
public class Glicemia {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Glicemia(Long id, java.sql.Date dataMedicao, java.sql.Date horaMedicao, Integer nivelGlicemico, String tipoGlicemia) {
		this.id = id;
		this.dataMedicao = dataMedicao;
		this.horaMedicao = horaMedicao;
		this.nivelGlicemico = nivelGlicemico;
		this.tipoGlicemia = tipoGlicemia;
	}
	
	public Glicemia(Long id) {
		this.id = id;
	}
	
	Glicemia(){
		
	}
	
	@Column(name="data_medicao")
	@DateTimeFormat(pattern= "dd/MM/yyyy")
	private java.sql.Date dataMedicao;
	
	@Column(name="hora_medicao")
	@DateTimeFormat(pattern= "HH:mm:ss")
	private java.sql.Date horaMedicao;
	
	@Column(name="nivel_glicemico")
	private Integer nivelGlicemico;
	
	@Column(name="tipo_glicemia")
	private String tipoGlicemia;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataMedicao() {
		return dataMedicao;
	}

	public void setDataMedicao(java.sql.Date dataMedicao) {
		this.dataMedicao = dataMedicao;
	}

	public Date getHoraMedicao() {
		return horaMedicao;
	}

	public void setHoraMedicao(java.sql.Date horaMedicao) {
		this.horaMedicao = horaMedicao;
	}

	public Integer getNivelGlicemico() {
		return nivelGlicemico;
	}

	public void setNivelGlicemico(Integer nivelGlicemico) {
		this.nivelGlicemico = nivelGlicemico;
	}

	public String getTipoGlicemia() {
		return tipoGlicemia;
	}

	public void setTipoGlicemia(String tipoGlicemia) {
		this.tipoGlicemia = tipoGlicemia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Glicemia other = (Glicemia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Glicemia [id=" + id + ", dataMedicao=" + dataMedicao + ", horaMedicao=" + horaMedicao
				+ ", nivelGlicemico=" + nivelGlicemico + ", tipoGlicemia=" + tipoGlicemia + "]";
	}
	
	
	
	
	
}
