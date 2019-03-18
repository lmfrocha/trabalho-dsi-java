package com.puc.dsiapi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String nome;
	
	@Size(min =3, max = 120)
	private String sobrenome;
	
	@Temporal(TemporalType.DATE)
	private Date dataNasc;

	private String sexo;
	
	private int telefone;
	
	@UniqueElements
	private String email;
	
	@UniqueElements
	private String login;
	
	private String senha;

	@ManyToOne
	@JoinColumn(name="paciente_FK", referencedColumnName="id", nullable=true)
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="medico_FK", referencedColumnName="id", nullable=true)
	private Medico medico;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa(Long id, @NotNull @Size(min = 3, max = 50) String nome, @Size(min = 3, max = 120) String sobrenome,
			Date dataNasc, String sexo, int telefone, @UniqueElements String email, @UniqueElements String login,
			String senha, @Nullable Paciente paciente, @Nullable Medico medico) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNasc = dataNasc;
		this.sexo = sexo;
		this.telefone = telefone;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.paciente = paciente;
		this.medico = medico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", dataNasc=" + dataNasc + ", sexo="
				+ sexo + ", telefone=" + telefone + ", email=" + email + ", login=" + login + "]";
	}
}
