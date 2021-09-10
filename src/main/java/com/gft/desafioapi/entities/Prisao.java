package com.gft.desafioapi.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Prisao {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="policial_id")
	Policial policial;
	
	@OneToOne
	Criminoso criminoso;
	
	@OneToOne
	Vitima vitima;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message= "Uma data deve ser informada.")
	@PastOrPresent(message="A data do crime deve ser passada.")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="delegacia_id")
	Delegacia delegacia;
	
	@ManyToOne
	@JoinColumn(name="delegado_id")
	Delegado delegado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Policial getPolicial() {
		return policial;
	}

	public void setPolicial(Policial policial) {
		this.policial = policial;
	}

	public Criminoso getCriminoso() {
		return criminoso;
	}

	public void setCriminoso(Criminoso criminoso) {
		this.criminoso = criminoso;
	}

	public Vitima getVitima() {
		return vitima;
	}

	public void setVitima(Vitima vitima) {
		this.vitima = vitima;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Delegacia getDelegacia() {
		return delegacia;
	}

	public void setDelegacia(Delegacia delegacia) {
		this.delegacia = delegacia;
	}

	public Delegado getDelegado() {
		return delegado;
	}

	public void setDelegado(Delegado delegado) {
		this.delegado = delegado;
	}	
	
}
