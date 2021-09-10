package com.gft.desafioapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.desafioapi.entities.Prisao;
import com.gft.desafioapi.repositories.PrisaoRepository;

@Service
public class PrisaoService {
	
	@Autowired
	private PrisaoRepository prisaoRepository;
	
	public Prisao atualizarPrisao(Long id, Prisao prisao) {
		Prisao encontradaPrisao = encontrarPrisaoPorId(id);
		BeanUtils.copyProperties(prisao, encontradaPrisao, "id");
		return prisaoRepository.save(encontradaPrisao);
	}
	
	public Prisao encontrarPrisaoPorId(Long id) {
		Optional<Prisao> encontradaPrisao = prisaoRepository.findById(id);
		if(encontradaPrisao.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return encontradaPrisao.get();
	}

}
