package com.puc.dsiapi.model;

import java.util.Date;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Glicemia(Long id, Date dataMedicao, Date horaMedicao, Integer nivelGlicemico, String tipoGlicemia) {
		super();
		this.id = id;
		this.dataMedicao = dataMedicao;
		this.horaMedicao = horaMedicao;
		this.nivelGlicemico = nivelGlicemico;
		this.tipoGlicemia = tipoGlicemia;
	}

	@DateTimeFormat(pattern= "dd/MM/yyyy")
	private Date dataMedicao;

	@DateTimeFormat(pattern= "HH:mm")
	private Date horaMedicao;
	
	private Integer nivelGlicemico;
	
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

	public void setDataMedicao(Date dataMedicao) {
		this.dataMedicao = dataMedicao;
	}

	public Date getHoraMedicao() {
		return horaMedicao;
	}

	public void setHoraMedicao(Date horaMedicao) {
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
