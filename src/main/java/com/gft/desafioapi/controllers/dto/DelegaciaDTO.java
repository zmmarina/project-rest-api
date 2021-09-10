package com.gft.desafioapi.controllers.dto;

import java.util.Optional;

import com.gft.desafioapi.entities.Delegacia;

public class DelegaciaDTO {
	
	private Long id;
	private String rua;
	private String numero;
	private String cidade;
	private String estado;
	private String cep;
	private String telefone;
	private String batalhao;
	
	
	public DelegaciaDTO() {}
	
	public DelegaciaDTO(Long id, String rua, String numero, String cidade, String estado, String cep, String telefone,
			String batalhao) {
		
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.telefone = telefone;
		this.batalhao = batalhao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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
	
	
	public static DelegaciaDTO from(Delegacia delegacia) {
		return new DelegaciaDTO(delegacia.getId(), delegacia.getEndereco().getRua(),delegacia.getEndereco().getNumero(),
				delegacia.getEndereco().getCidade(), delegacia.getEndereco().getEstado(), delegacia.getEndereco().getCep(),
				delegacia.getTelefone(), delegacia.getBatalhao());
	}

	public static DelegaciaDTO from(Optional<Delegacia> encontradaDelegacia) {
		return new DelegaciaDTO(encontradaDelegacia.get().getId(), encontradaDelegacia.get().getEndereco().getRua(),
				encontradaDelegacia.get().getEndereco().getNumero(), encontradaDelegacia.get().getEndereco().getCidade(), 
				encontradaDelegacia.get().getEndereco().getEstado(), encontradaDelegacia.get().getEndereco().getCep(),
				encontradaDelegacia.get().getTelefone(), encontradaDelegacia.get().getBatalhao());
	}

}
