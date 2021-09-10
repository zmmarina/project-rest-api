package com.gft.desafioapi.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.desafioapi.entities.Delegado;
import com.gft.desafioapi.repositories.DelegadoRepository;

@Service
public class DelegadoService {
	
	@Autowired
	private DelegadoRepository delegadoRepository;
	
	public Delegado atualizarDelegado(Long id, Delegado delegado) {
		Delegado encontradoDelegado = encontrarDelegadoPorId(id);
		BeanUtils.copyProperties(delegado, encontradoDelegado, "id");
		return delegadoRepository.save(encontradoDelegado);
	}
	
	public Delegado encontrarDelegadoPorId(Long id) {
		Optional<Delegado> encontradoDelegado = delegadoRepository.findById(id);
		if(encontradoDelegado.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return encontradoDelegado.get();
	}
	
	public List<Delegado> delegadoPorOrdemAsc(){
		List<Delegado> delegadosList = delegadoRepository.findAll();
		delegadosList.sort(Comparator.comparing(Delegado :: getNome));
		
		return delegadosList;
	}
	
	public List<Delegado> delegadoPorOrdemDesc(){
		List<Delegado> delegadosList = delegadoRepository.findAll();
		delegadosList.sort(Comparator.comparing(Delegado :: getNome).reversed());
		
		return delegadosList;
	}

}
