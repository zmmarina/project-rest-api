package com.gft.desafioapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.desafioapi.entities.Legista;
import com.gft.desafioapi.repositories.LegistaRepository;

@Service
public class LegistaService {
	
	@Autowired
	private LegistaRepository legistaRepository;
	
	public Legista atualizarLegista(Long id, Legista legista) {
		Legista encontradoLegista = encontrarLegistaPorId(id);
		BeanUtils.copyProperties(legista, encontradoLegista, "id");
		return legistaRepository.save(encontradoLegista);
	}
	
	public Legista encontrarLegistaPorId(Long id) {
		Optional<Legista> encontradoLegista = legistaRepository.findById(id);
		if(encontradoLegista.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return encontradoLegista.get();
	}

}
