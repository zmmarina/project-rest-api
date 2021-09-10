package com.gft.desafioapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.desafioapi.entities.Crime;
import com.gft.desafioapi.repositories.CrimeRepository;

@Service
public class CrimeService {
	
	@Autowired
	private CrimeRepository crimeRepository;
	
	public Crime atualizarCrime(Long id, Crime crime) {
		Crime encontradoCrime = encontrarCrimePorId(id);
		BeanUtils.copyProperties(crime, encontradoCrime, "id");
		return crimeRepository.save(encontradoCrime);
	}
	
	public Crime encontrarCrimePorId(Long id) {
		Optional<Crime> encontradoCrime = crimeRepository.findById(id);
		if(encontradoCrime.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return encontradoCrime.get();
	}

}
