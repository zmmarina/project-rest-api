package com.gft.desafioapi.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Laudo {
	
	private String causa;
	private String forma;
	private String detalhes;
	
	
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	public String getForma() {
		return forma;
	}
	public void setForma(String forma) {
		this.forma = forma;
	}
	public String getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	

}
