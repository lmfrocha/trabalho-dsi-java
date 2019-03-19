package com.puc.dsiapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "paciente")
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String peso;
	
	public Paciente(Long id, String peso, float altura, @Nullable Diabete diabete) {
		this.id = id;
		this.peso = peso;
		this.altura = altura;
		this.diabete = diabete;
	}

	public Paciente (Long id) {
		this.id=id;
	}
	
	Paciente(){
		
	}
	
	private float altura;
	
	@ManyToOne
	@NotFound(action= NotFoundAction.IGNORE)
	@JoinColumn(name= "diabete", referencedColumnName="id")
	private Diabete diabete;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public Diabete getDiabete() {
		return diabete;
	}

	public void setDiabete(Diabete diabete) {
		this.diabete = diabete;
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
		Paciente other = (Paciente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

	
}
