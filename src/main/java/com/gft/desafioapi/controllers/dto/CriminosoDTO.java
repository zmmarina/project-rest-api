package com.gft.desafioapi.controllers.dto;

import java.util.Optional;

import com.gft.desafioapi.entities.Criminoso;

public class CriminosoDTO {
	
	private Long id;
	private String nome;
	private String cpf;
	
	
	public CriminosoDTO() {	}


	public CriminosoDTO(Long id, String nome, String cpf) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}


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


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public static CriminosoDTO from (Criminoso criminoso) {
		return new CriminosoDTO(criminoso.getId(), criminoso.getNome(), criminoso.getCpf());
	}

	public static CriminosoDTO from(Optional<Criminoso> encontradoCriminoso) {
		return new CriminosoDTO(encontradoCriminoso.get().getId(), encontradoCriminoso.get().getNome(), 
				encontradoCriminoso.get().getCpf());
	}
	

}
