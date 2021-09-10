package com.gft.desafioapi.controllers.dto;

import java.util.Optional;

import com.gft.desafioapi.entities.Legista;

public class LegistaDTO {
	
	private Long id;
	private String nome;
	private String crm;
	
	
	public LegistaDTO() {	}


	public LegistaDTO(Long id, String nome, String crm) {
		this.id = id;
		this.nome = nome;
		this.crm = crm;
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


	public String getCrm() {
		return crm;
	}


	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	public static LegistaDTO from (Legista legista) {
		return new LegistaDTO(legista.getId(), legista.getNome(), legista.getCrm());
	}

	public static LegistaDTO from(Optional<Legista> encontradoLegista) {
		return new LegistaDTO(encontradoLegista.get().getId(), encontradoLegista.get().getNome(), 
				encontradoLegista.get().getCrm());
	}

}
