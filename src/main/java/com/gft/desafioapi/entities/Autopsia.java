package com.gft.desafioapi.entities;

import java.time.LocalDate;

import javax.persistence.Embedded;
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
public class Autopsia {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Vitima vitima;
	
	@ManyToOne
	@JoinColumn(name="legista_id")
	private Legista legista;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message= "Uma data deve ser informada.")
	@PastOrPresent(message="A data do crime deve ser passada.")
	private LocalDate date;
	
	@Embedded
	private Laudo laudo;
	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Vitima getVitima() {
		return vitima;
	}

	public void setVitima(Vitima vitima) {
		this.vitima = vitima;
	}

	public Legista getLegista() {
		return legista;
	}

	public void setLegista(Legista legista) {
		this.legista = legista;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Laudo getLaudo() {
		return laudo;
	}

	public void setLaudo(Laudo laudo) {
		this.laudo = laudo;
	}
	

}
