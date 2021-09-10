package com.gft.desafioapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.desafioapi.entities.Criminoso;
import com.gft.desafioapi.repositories.CriminosoRepository;

@Service
public class CriminosoService {
	
	@Autowired
	private CriminosoRepository criminosoRepository;
	
	public Criminoso atualizarCriminoso(Long id, Criminoso criminoso) {
		Criminoso encontradoCriminoso = encontrarCriminosoPorId(id);
		BeanUtils.copyProperties(criminoso, encontradoCriminoso, "id");
		return criminosoRepository.save(encontradoCriminoso);
	}
	
	public Criminoso encontrarCriminosoPorId(Long id) {
		Optional<Criminoso> encontradoCriminoso = criminosoRepository.findById(id);
		if(encontradoCriminoso.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return encontradoCriminoso.get();
	}

}
