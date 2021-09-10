package com.gft.desafioapi.controllers.dto;

import java.time.LocalDate;
import java.util.Optional;

import com.gft.desafioapi.entities.Prisao;

public class PrisaoDTO {
	
	private Long id;
	private Long idVitima;
	private String nomeVitima;
	private String cpfVitima;
	private Long idPolicial;
	private String nomePolicial;
	private String funcionalPolicial;
	private Long idCriminoso;
	private String nomeCriminoso;
	private String cpfCriminoso;
	private LocalDate date;
	private Long idDelegacia;
	private Long idDelegado;
	private String nomeDelegado;
	private String funcionalDelegado;
	
	
	public PrisaoDTO() {}

	public PrisaoDTO(Long id, Long idVitima, String nomeVitima, String cpfVitima, Long idPolicial, String nomePolicial,
			String funcionalPolicial, Long idCriminoso, String nomeCriminoso, String cpfCriminoso, LocalDate date,
			Long idDelegacia, Long idDelegado, String nomeDelegado, String funcionalDelegado) {
		this.id = id;
		this.idVitima = idVitima;
		this.nomeVitima = nomeVitima;
		this.cpfVitima = cpfVitima;
		this.idPolicial = idPolicial;
		this.nomePolicial = nomePolicial;
		this.funcionalPolicial = funcionalPolicial;
		this.idCriminoso = idCriminoso;
		this.nomeCriminoso = nomeCriminoso;
		this.cpfCriminoso = cpfCriminoso;
		this.date = date;
		this.idDelegacia = idDelegacia;
		this.idDelegado = idDelegado;
		this.nomeDelegado = nomeDelegado;
		this.funcionalDelegado = funcionalDelegado;
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

	public Long getIdPolicial() {
		return idPolicial;
	}

	public void setIdPolicial(Long idPolicial) {
		this.idPolicial = idPolicial;
	}

	public String getNomePolicial() {
		return nomePolicial;
	}

	public void setNomePolicial(String nomePolicial) {
		this.nomePolicial = nomePolicial;
	}

	public String getFuncionalPolicial() {
		return funcionalPolicial;
	}

	public void setFuncionalPolicial(String funcionalPolicial) {
		this.funcionalPolicial = funcionalPolicial;
	}

	public Long getIdCriminoso() {
		return idCriminoso;
	}

	public void setIdCriminoso(Long idCriminoso) {
		this.idCriminoso = idCriminoso;
	}

	public String getNomeCriminoso() {
		return nomeCriminoso;
	}

	public void setNomeCriminoso(String nomeCriminoso) {
		this.nomeCriminoso = nomeCriminoso;
	}

	public String getCpfCriminoso() {
		return cpfCriminoso;
	}

	public void setCpfCriminoso(String cpfCriminoso) {
		this.cpfCriminoso = cpfCriminoso;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getIdDelegacia() {
		return idDelegacia;
	}

	public void setIdDelegacia(Long idDelegacia) {
		this.idDelegacia = idDelegacia;
	}

	public Long getIdDelegado() {
		return idDelegado;
	}

	public void setIdDelegado(Long idDelegado) {
		this.idDelegado = idDelegado;
	}

	public String getNomeDelegado() {
		return nomeDelegado;
	}

	public void setNomeDelegado(String nomeDelegado) {
		this.nomeDelegado = nomeDelegado;
	}

	public String getFuncionalDelegado() {
		return funcionalDelegado;
	}

	public void setFuncionalDelegado(String funcionalDelegado) {
		this.funcionalDelegado = funcionalDelegado;
	}
	
	
	public static PrisaoDTO from(Prisao prisao) {
		return new PrisaoDTO(prisao.getId(), prisao.getVitima().getId(), prisao.getVitima().getNome(),
				prisao.getVitima().getCpf(), prisao.getPolicial().getId(), prisao.getPolicial().getNome(), 
				prisao.getPolicial().getFuncional(), prisao.getCriminoso().getId(), prisao.getCriminoso().getNome(),
				prisao.getCriminoso().getCpf(), prisao.getDate(), prisao.getDelegacia().getId(), prisao.getDelegado().getId(),
				prisao.getDelegado().getNome(), prisao.getDelegado().getFuncional());
	}

	public static PrisaoDTO from(Optional<Prisao> encontradaPrisao) {
		return new PrisaoDTO(encontradaPrisao.get().getId(), encontradaPrisao.get().getVitima().getId(), encontradaPrisao.get().getVitima().getNome(),
				encontradaPrisao.get().getVitima().getCpf(), encontradaPrisao.get().getPolicial().getId(), encontradaPrisao.get().getPolicial().getNome(), 
				encontradaPrisao.get().getPolicial().getFuncional(), encontradaPrisao.get().getCriminoso().getId(), encontradaPrisao.get().getCriminoso().getNome(),
				encontradaPrisao.get().getCriminoso().getCpf(), encontradaPrisao.get().getDate(), encontradaPrisao.get().getDelegacia().getId(), encontradaPrisao.get().getDelegado().getId(),
				encontradaPrisao.get().getDelegado().getNome(), encontradaPrisao.get().getDelegado().getFuncional());
	}
	
	

}
