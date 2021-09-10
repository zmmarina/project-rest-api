package com.gft.desafioapi.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Crime {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
		
	@ManyToOne
	@JoinColumn(name="criminoso_id")
	private Criminoso criminoso;
	
	@OneToOne
	private Vitima vitima;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message= "Uma data deve ser informada.")
	@PastOrPresent(message="A data do crime deve ser passada.")
	private LocalDate date;
	
	@NotEmpty(message="Por favor, informe descrição do crime.")
	private String descricao;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
