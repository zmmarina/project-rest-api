package com.gft.desafioapi.entities;

public enum Patente {
	
	
	SOLDADO("soldado"), 
	CABO("cabo"), 
	SARGENTO("sargento"), 
	SUBTENENTE("subtenente"), 
	TENENTE("tenente"),
	CAPITAO("capitao"), 
	MAJOR("major"), 
	CORONEL("coronel"); 
	
	
	private String patente;
	
	Patente(String patente){
		this.patente = patente;
	}
	
	public String getPatente() {
		return this.patente;
	} 

}
