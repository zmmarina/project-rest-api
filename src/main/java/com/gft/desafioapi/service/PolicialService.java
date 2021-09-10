package com.gft.desafioapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.desafioapi.entities.Policial;
import com.gft.desafioapi.repositories.PolicialRepository;

@Service
public class PolicialService {

	@Autowired
	private PolicialRepository policialRepository;
	
	public Policial atualizarPolicial(Long id, Policial policial) {
		Policial encontradoPolicial = encontrarPolicialPorId(id);
		BeanUtils.copyProperties(policial, encontradoPolicial, "id");
		return policialRepository.save(encontradoPolicial);
	}
	
	public Policial encontrarPolicialPorId(Long id) {
		Optional<Policial> encontradoPolicial = policialRepository.findById(id);
		if(encontradoPolicial.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return encontradoPolicial.get();
	}

}
