package com.gft.desafioapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.desafioapi.entities.Autopsia;
import com.gft.desafioapi.repositories.AutopsiaRepository;

@Service
public class AutopsiaService {
	
	@Autowired
	private AutopsiaRepository autopsiaRepository;
	
	public Autopsia atualizarAutopsia(Long id, Autopsia autopsia) {
		Autopsia encontradaAutopsia = encontrarAutopsiaPorId(id);
		BeanUtils.copyProperties(autopsia, encontradaAutopsia, "id");
		return autopsiaRepository.save(encontradaAutopsia);
	}
	
	public Autopsia encontrarAutopsiaPorId(Long id) {
		Optional<Autopsia> encontradaAutopsia = autopsiaRepository.findById(id);
		if(encontradaAutopsia.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return encontradaAutopsia.get();
	}

}
