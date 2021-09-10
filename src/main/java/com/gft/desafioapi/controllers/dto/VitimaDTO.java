package com.gft.desafioapi.controllers.dto;

import java.util.Optional;

import com.gft.desafioapi.entities.Vitima;

public class VitimaDTO {
	
	private Long id;
	private String nome;
	private String cpf;
	
	
	public VitimaDTO() {	}


	public VitimaDTO(Long id, String nome, String cpf) {
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
	
	public static VitimaDTO from (Vitima vitima) {
		return new VitimaDTO(vitima.getId(), vitima.getNome(), vitima.getCpf());
	}

	public static VitimaDTO from(Optional<Vitima> encontradoVitima) {
		return new VitimaDTO(encontradoVitima.get().getId(), encontradoVitima.get().getNome(), 
				encontradoVitima.get().getCpf());
	}

}
