package com.gft.desafioapi.controllers.dto;

import java.util.Optional;

import com.gft.desafioapi.entities.Policial;

public class PolicialDTO {
private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String funcional;
	private String patente;
	
	public PolicialDTO() {
		super();
	}

	public PolicialDTO(Long id, String nome, String funcional, String patente) {
		this.id = id;
		this.nome = nome;
		this.funcional = funcional;
		this.patente = patente;
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

	public String getFuncional() {
		return funcional;
	}

	public void setFuncional(String funcional) {
		this.funcional = funcional;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public static PolicialDTO from (Policial policial) {
		return new PolicialDTO(policial.getId(), policial.getNome(), policial.getFuncional(),
				policial.getPatente().name());
	}

	public static PolicialDTO from(Optional<Policial> encontradoPolicial) {
		return new PolicialDTO(encontradoPolicial.get().getId(), encontradoPolicial.get().getNome(), 
				encontradoPolicial.get().getFuncional(), encontradoPolicial.get().getPatente().getPatente());
	}
	

}
