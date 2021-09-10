package com.gft.desafioapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Policial {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message= "Por favor, informe um nome válido.")
	@Size(min=4, max=100, message = "O nome deve conter entre 4 e 100 letras.")
	private String nome;
	
	@NotEmpty(message= "Por favor, informe uma funcional válida.")
	@Size(min=4, max=100, message = "A funcional deve conter entre 4 e 100 caracteres.")
	@Column(unique= true)
	private String funcional;
	
	@Enumerated(EnumType.STRING)
	//@NotEmpty(message= "Por favor, informe um turno.")
	private Patente patente;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncional() {
		return funcional;
	}

	public void setFuncional(String funcional) {
		this.funcional = funcional;
	}

	public Patente getPatente() {
		return patente;
	}

	public void setPatente(Patente patente) {
		this.patente = patente;
	}
	
}
