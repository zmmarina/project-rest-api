package com.gft.desafioapi.entities;

public enum Turno {
	
	MANHA("manha"),	
	TARDE("tarde"),	
	NOITE("noite"),	
	MADRUGADA("madrugada");
	
	
	private String turno;
	
	Turno(String turno){
		this.turno = turno;
	}
	
	public String getTurno() {
		return this.turno;
	} 
}
