package com.gft.desafioapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.desafioapi.controllers.dto.PrisaoDTO;
import com.gft.desafioapi.entities.Prisao;
import com.gft.desafioapi.events.CreatedResourceEvent;
import com.gft.desafioapi.repositories.PrisaoRepository;
import com.gft.desafioapi.service.PrisaoService;

@RestController
@RequestMapping("/prisao")
public class PrisaoController {
	
	@Autowired
	private PrisaoRepository prisaoRepository;
	
	@Autowired
	private PrisaoService prisaoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<PrisaoDTO> listarPrisaos(){
		
		List<PrisaoDTO> prisaoDTOLista = new ArrayList<>();
		List<Prisao> prisaoLista = prisaoRepository.findAll();
		
		for(Prisao prisao : prisaoLista) {
			PrisaoDTO prisaoDTO = PrisaoDTO.from(prisao);
			prisaoDTOLista.add(prisaoDTO);
		}
		return prisaoDTOLista;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PrisaoDTO> encontrarPrisaoPorId(@PathVariable Long id){
		Optional<Prisao> encontradaPrisao = prisaoRepository.findById(id);
		PrisaoDTO prisaoDTO = PrisaoDTO.from(encontradaPrisao);
		return !encontradaPrisao.isEmpty() ? ResponseEntity.ok(prisaoDTO) : ResponseEntity.notFound().build();
	}
		
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('DELEGADO')")
	public ResponseEntity<PrisaoDTO> atualizarPrisao(@PathVariable Long id, @Valid @RequestBody Prisao prisao){
		Prisao salvaPrisao = prisaoService.atualizarPrisao(id, prisao);
		PrisaoDTO prisaoDTO = PrisaoDTO.from(salvaPrisao);
		return ResponseEntity.ok(prisaoDTO);
	}	
	
	@PostMapping
	@PreAuthorize("hasAuthority('DELEGADO')")
	public ResponseEntity<PrisaoDTO> criarPrisao (@Valid @RequestBody Prisao prisao, HttpServletResponse response){
		Prisao salvaPrisao = prisaoRepository.save(prisao);
		publisher.publishEvent(new CreatedResourceEvent(this, response, salvaPrisao.getId()));
		PrisaoDTO prisaoDTO = PrisaoDTO.from(salvaPrisao);
		return ResponseEntity.status(HttpStatus.CREATED).body(prisaoDTO);
	}
	
	/*
	 * @DeleteMapping("/{id}")
	 * 
	 * @ResponseStatus(HttpStatus.NO_CONTENT)
	 * 
	 * @PreAuthorize("hasAuthority('DELEGADO')") public void
	 * deletarPrisao(@PathVariable Long id) { prisaoRepository.deleteById(id); }
	 */

}
