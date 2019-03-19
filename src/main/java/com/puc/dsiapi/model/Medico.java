package com.puc.dsiapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name= "medico")
public class Medico {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Medico(Long id, String cpf, String crm, String uf, @Nullable Paciente paciente) {
		this.id = id;
		this.cpf = cpf;
		this.crm = crm;
		this.uf = uf;
		this.paciente = paciente;
	}

	public Medico(Long id){
		this.id = id;
	}
	
	Medico(){
		
	}
	
	@Column(name="cpf")
	private String cpf;
	
	@Column(name="crm")
	private String crm;
	
	@Column(name="uf")
	private String uf;
	
	@Column(name="endereco_consultorio")
	private String endereco;
	
	@ManyToOne
	@JoinColumn(name="paciente", referencedColumnName="id")
	private Paciente paciente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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
		Medico other = (Medico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Medico [id=" + id + ", cpf=" + cpf + ", crm=" + crm + ", uf=" + uf + ", endereco=" + endereco
				+ ", paciente=" + paciente + "]";
	}
	
	
}
