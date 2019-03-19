package com.puc.dsiapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_pessoa")
public class TipoPessoa {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="medico", referencedColumnName="id")
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name="paciente", referencedColumnName="id")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="pessoa", referencedColumnName="id")
	private Pessoa pessoa;
	
	public TipoPessoa(Long id, Medico medico, Paciente paciente) {
		super();
		this.id = id;
		this.medico = medico;
		this.paciente = paciente;
	}
	
	TipoPessoa() {
		
	}
	
	public TipoPessoa(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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
		TipoPessoa other = (TipoPessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TipoPessoa [id=" + id + ", medico=" + medico + ", paciente=" + paciente + "]";
	}
	
}
