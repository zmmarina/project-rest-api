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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.desafioapi.controllers.dto.CriminosoDTO;
import com.gft.desafioapi.entities.Criminoso;
import com.gft.desafioapi.events.CreatedResourceEvent;
import com.gft.desafioapi.repositories.CriminosoRepository;
import com.gft.desafioapi.service.CriminosoService;

@RestController
@RequestMapping("/criminoso")
public class CriminosoController {
	
	@Autowired
	private CriminosoRepository criminosoRepository;
	
	@Autowired
	private CriminosoService criminosoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public List<CriminosoDTO> listarCriminosos(){
		
		List<CriminosoDTO> criminosoDTOLista = new ArrayList<>();
		List<Criminoso> criminosoLista = criminosoRepository.findAll();
		
		for(Criminoso criminoso : criminosoLista) {
			CriminosoDTO criminosoDTO = CriminosoDTO.from(criminoso);
			criminosoDTOLista.add(criminosoDTO);
		}
		return criminosoDTOLista;
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public ResponseEntity<CriminosoDTO> encontrarCriminosoPorId(@PathVariable Long id){
		Optional<Criminoso> encontradoCriminoso = criminosoRepository.findById(id);
		CriminosoDTO criminosoDTO = CriminosoDTO.from(encontradoCriminoso);
		return !encontradoCriminoso.isEmpty() ? ResponseEntity.ok(criminosoDTO) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<CriminosoDTO> criarCriminoso (@Valid @RequestBody Criminoso criminoso, HttpServletResponse response){
		Criminoso salvoCriminoso = criminosoRepository.save(criminoso);
		publisher.publishEvent(new CreatedResourceEvent(this, response, salvoCriminoso.getId()));
		CriminosoDTO criminosoDTO = CriminosoDTO.from(salvoCriminoso);
		return ResponseEntity.status(HttpStatus.CREATED).body(criminosoDTO);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<CriminosoDTO> atualizarCriminoso(@PathVariable Long id, @Valid @RequestBody Criminoso criminoso){
		Criminoso salvoCriminoso = criminosoService.atualizarCriminoso(id, criminoso);
		CriminosoDTO criminosoDTO = CriminosoDTO.from(salvoCriminoso);
		return ResponseEntity.ok(criminosoDTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('JUIZ')")
	public void deletarCriminoso(@PathVariable Long id) {
		criminosoRepository.deleteById(id);
	}


}
