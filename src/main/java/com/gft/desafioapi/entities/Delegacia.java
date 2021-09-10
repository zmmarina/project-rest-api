package com.gft.desafioapi.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Delegacia {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private Endereco endereco;	
	
	@NotEmpty(message= "Por favor, informe um telefone válido.")
	@Size(min=7, max=20, message = "O nome deve conter entre 7 e 20 números.")
	private String telefone;
	
	@NotEmpty(message= "Por favor, informe um batalhão válido.")
	@Size(min=4, max=100, message = "O nome deve conter entre 4 e 100 letras.")
	private String batalhao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getBatalhao() {
		return batalhao;
	}
	public void setBatalhao(String batalhao) {
		this.batalhao = batalhao;
	}	

}
