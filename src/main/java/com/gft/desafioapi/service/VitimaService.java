package com.gft.desafioapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.desafioapi.entities.Vitima;
import com.gft.desafioapi.repositories.VitimaRepository;

@Service
public class VitimaService {
	
	@Autowired
	private VitimaRepository vitimaRepository;
	
	public Vitima atualizarVitima(Long id, Vitima vitima) {
		Vitima encontradaVitima = encontrarVitimaPorId(id);
		BeanUtils.copyProperties(vitima, encontradaVitima, "id");
		return vitimaRepository.save(encontradaVitima);
	}
	
	public Vitima encontrarVitimaPorId(Long id) {
		Optional<Vitima> encontradaVitima = vitimaRepository.findById(id);
		if(encontradaVitima.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return encontradaVitima.get();
	}


}
