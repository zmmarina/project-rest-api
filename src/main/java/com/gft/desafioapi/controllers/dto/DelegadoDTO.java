package com.gft.desafioapi.controllers.dto;

import java.io.Serializable;
import java.util.Optional;

import com.gft.desafioapi.entities.Delegado;

public class DelegadoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private Long delegaciaId;
	private String funcional;
	private String turno;
	
	
	public DelegadoDTO() {}
	
	public DelegadoDTO(Long id, String nome, Long delegaciaId, String funcional, String turno) {
		this.id = id;
		this.nome = nome;
		this.delegaciaId = delegaciaId;
		this.funcional = funcional;
		this.turno = turno;
	}	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getDelegaciaId() {
		return delegaciaId;
	}

	public void setDelegaciaId(Long delegaciaId) {
		this.delegaciaId = delegaciaId;
	}

	public String getFuncional() {
		return funcional;
	}

	public void setFuncional(String funcional) {
		this.funcional = funcional;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public static DelegadoDTO from (Delegado delegado) {
		return new DelegadoDTO(delegado.getId(), delegado.getNome(), delegado.getDelegacia().getId(), delegado.getFuncional(),
				delegado.getTurno().name());
	}

	public static DelegadoDTO from(Optional<Delegado> encontradoDelegado) {
		return new DelegadoDTO(encontradoDelegado.get().getId(), encontradoDelegado.get().getNome(), 
				encontradoDelegado.get().getDelegacia().getId(), encontradoDelegado.get().getFuncional(),
				encontradoDelegado.get().getTurno().getTurno());
	}
	

}
