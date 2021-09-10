package com.gft.desafioapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.desafioapi.entities.Delegacia;
import com.gft.desafioapi.repositories.DelegaciaRepository;

@Service
public class DelegaciaService {
	
	@Autowired
	private DelegaciaRepository delegaciaRepository;
	
	public Delegacia atualizarDelegacia(Long id, Delegacia delegacia) {
		Delegacia encontradaDelegacia = encontrarDelegaciaPorId(id);
		BeanUtils.copyProperties(delegacia, encontradaDelegacia, "id");
		return delegaciaRepository.save(encontradaDelegacia);
	}
	
	public Delegacia encontrarDelegaciaPorId(Long id) {
		Optional<Delegacia> encontradaDelegacia = delegaciaRepository.findById(id);
		if(encontradaDelegacia.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return encontradaDelegacia.get();
	}
	
	
}
