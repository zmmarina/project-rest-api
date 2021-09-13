package com.gft.desafioapi.controllers.dto;

import java.time.LocalDate;
import java.util.Optional;

import com.gft.desafioapi.entities.Autopsia;

public class AutopsiaDTO {
	
	private Long id;
	private Long idVitima;
	private String nomeVitima;
	private String cpfVitima;
	private Long idLegista;
	private String crmLegista;
	private LocalDate date;
	private String causa;
	private String forma;
	private String detalhes;
	
	
	public AutopsiaDTO() {}
	
	public AutopsiaDTO(Long id, Long idVitima, String nomeVitima, String cpfVitima, Long idLegista, String crmLegista,
			LocalDate date, String causa, String forma, String detalhes) {
		this.id = id;
		this.idVitima = idVitima;
		this.nomeVitima = nomeVitima;
		this.cpfVitima = cpfVitima;
		this.idLegista = idLegista;
		this.crmLegista = crmLegista;
		this.date = date;
		this.causa = causa;
		this.forma = forma;
		this.detalhes = detalhes;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getIdVitima() {
		return idVitima;
	}


	public void setIdVitima(Long idVitima) {
		this.idVitima = idVitima;
	}


	public String getNomeVitima() {
		return nomeVitima;
	}


	public void setNomeVitima(String nomeVitima) {
		this.nomeVitima = nomeVitima;
	}


	public String getCpfVitima() {
		return cpfVitima;
	}


	public void setCpfVitima(String cpfVitima) {
		this.cpfVitima = cpfVitima;
	}


	public Long getIdLegista() {
		return idLegista;
	}


	public void setIdLegista(Long idLegista) {
		this.idLegista = idLegista;
	}


	public String getCrmLegista() {
		return crmLegista;
	}


	public void setCrmLegista(String crmLegista) {
		this.crmLegista = crmLegista;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


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
	
	public static AutopsiaDTO from(Autopsia autopsia) {
		return new AutopsiaDTO(autopsia.getId(), autopsia.getVitima().getId(), autopsia.getVitima().getNome(),
				autopsia.getVitima().getCpf(), autopsia.getLegista().getId(), autopsia.getLegista().getCrm(),
				autopsia.getDate(), autopsia.getLaudo().getCausa(), autopsia.getLaudo().getForma(), 
				autopsia.getLaudo().getDetalhes());
	}


	public static AutopsiaDTO from(Optional<Autopsia> encontradaAutopsia) {
		return new AutopsiaDTO(encontradaAutopsia.get().getId(), encontradaAutopsia.get().getVitima().getId(),
				encontradaAutopsia.get().getVitima().getNome(), encontradaAutopsia.get().getVitima().getCpf(),
				encontradaAutopsia.get().getLegista().getId(), encontradaAutopsia.get().getLegista().getCrm(),
				encontradaAutopsia.get().getDate(), encontradaAutopsia.get().getLaudo().getCausa(), 
				encontradaAutopsia.get().getLaudo().getForma(), encontradaAutopsia.get().getLaudo().getDetalhes());
	}

}
