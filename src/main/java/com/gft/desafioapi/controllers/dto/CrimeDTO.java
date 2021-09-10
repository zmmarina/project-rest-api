package com.gft.desafioapi.controllers.dto;

import java.time.LocalDate;
import java.util.Optional;

import com.gft.desafioapi.entities.Crime;

public class CrimeDTO {
	
	private Long id;
	private Long criminosoId;
	private String criminosoNome;
	private String criminosoCpf;
	private Long vitimaId;
	private String vitimaNome;
	private String vitimaCpf;
	private LocalDate date;
	private String descricao;
	
	
	public CrimeDTO() {	}


	public CrimeDTO(Long id, Long criminosoId, String criminosoNome, String criminosoCpf, Long vitimaId,
			String vitimaNome, String vitimaCpf, LocalDate date, String descricao) {
		this.id = id;
		this.criminosoId = criminosoId;
		this.criminosoNome = criminosoNome;
		this.criminosoCpf = criminosoCpf;
		this.vitimaId = vitimaId;
		this.vitimaNome = vitimaNome;
		this.vitimaCpf = vitimaCpf;
		this.date = date;
		this.descricao = descricao;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCriminosoId() {
		return criminosoId;
	}


	public void setCriminosoId(Long criminosoId) {
		this.criminosoId = criminosoId;
	}


	public String getCriminosoNome() {
		return criminosoNome;
	}


	public void setCriminosoNome(String criminosoNome) {
		this.criminosoNome = criminosoNome;
	}


	public String getCriminosoCpf() {
		return criminosoCpf;
	}


	public void setCriminosoCpf(String criminosoCpf) {
		this.criminosoCpf = criminosoCpf;
	}


	public Long getVitimaId() {
		return vitimaId;
	}


	public void setVitimaId(Long vitimaId) {
		this.vitimaId = vitimaId;
	}


	public String getVitimaNome() {
		return vitimaNome;
	}


	public void setVitimaNome(String vitimaNome) {
		this.vitimaNome = vitimaNome;
	}


	public String getVitimaCpf() {
		return vitimaCpf;
	}


	public void setVitimaCpf(String vitimaCpf) {
		this.vitimaCpf = vitimaCpf;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	public static CrimeDTO from(Crime crime) {
		return new CrimeDTO(crime.getId(), crime.getCriminoso().getId(), crime.getCriminoso().getNome(),
				crime.getCriminoso().getCpf(), crime.getVitima().getId(), crime.getVitima().getNome(),
				crime.getVitima().getCpf(), crime.getDate(), crime.getDescricao());
	}


	public static CrimeDTO from(Optional<Crime> encontradoCrime) {
		return new CrimeDTO(encontradoCrime.get().getId(), encontradoCrime.get().getCriminoso().getId(),
				encontradoCrime.get().getCriminoso().getNome(), encontradoCrime.get().getCriminoso().getCpf(),
				encontradoCrime.get().getVitima().getId(), encontradoCrime.get().getVitima().getNome(), 
				encontradoCrime.get().getVitima().getCpf(), encontradoCrime.get().getDate(), encontradoCrime.get().getDescricao());
	}
	
	

}
