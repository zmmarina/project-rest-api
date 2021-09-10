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

import com.gft.desafioapi.controllers.dto.AutopsiaDTO;
import com.gft.desafioapi.entities.Autopsia;
import com.gft.desafioapi.events.CreatedResourceEvent;
import com.gft.desafioapi.repositories.AutopsiaRepository;
import com.gft.desafioapi.service.AutopsiaService;

@RestController
@RequestMapping("/autopsia")
public class AutopsiaController {
	
	@Autowired
	private AutopsiaRepository autopsiaRepository;
	
	@Autowired
	private AutopsiaService autopsiaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('DELEGADO') or hasAuthority('ADVOGADO')")
	public List<AutopsiaDTO> listarAutopsias(){
		
		List<AutopsiaDTO> autopsiaDTOLista = new ArrayList<>();
		List<Autopsia> autopsiaLista = autopsiaRepository.findAll();
		
		for(Autopsia autopsia : autopsiaLista) {
			AutopsiaDTO autopsiaDTO = AutopsiaDTO.from(autopsia);
			autopsiaDTOLista.add(autopsiaDTO);
		}
		return autopsiaDTOLista;
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('DELEGADO') or hasAuthority('ADVOGADO')")
	public ResponseEntity<AutopsiaDTO> encontrarAutopsiaPorId(@PathVariable Long id){
		Optional<Autopsia> encontradaAutopsia = autopsiaRepository.findById(id);
		AutopsiaDTO autopsiaDTO = AutopsiaDTO.from(encontradaAutopsia);
		return !encontradaAutopsia.isEmpty() ? ResponseEntity.ok(autopsiaDTO) : ResponseEntity.notFound().build();
	}
		
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('DELEGADO')")
	public ResponseEntity<AutopsiaDTO> atualizarAutopsia(@PathVariable Long id, @Valid @RequestBody Autopsia autopsia){
		Autopsia salvaAutopsia = autopsiaService.atualizarAutopsia(id, autopsia);
		AutopsiaDTO autopsiaDTO = AutopsiaDTO.from(salvaAutopsia);
		return ResponseEntity.ok(autopsiaDTO);
	}	
	
	@PostMapping
	@PreAuthorize("hasAuthority('DELEGADO')")
	public ResponseEntity<AutopsiaDTO> criarAutopsia (@Valid @RequestBody Autopsia autopsia, HttpServletResponse response){
		Autopsia salvaAutopsia = autopsiaRepository.save(autopsia);
		publisher.publishEvent(new CreatedResourceEvent(this, response, salvaAutopsia.getId()));
		AutopsiaDTO autopsiaDTO = AutopsiaDTO.from(salvaAutopsia);
		return ResponseEntity.status(HttpStatus.CREATED).body(autopsiaDTO);
	}
	
	/*
	 * @DeleteMapping("/{id}")
	 * 
	 * @ResponseStatus(HttpStatus.NO_CONTENT)
	 * 
	 * @PreAuthorize("hasAuthority('DELEGADO')") public void
	 * deletarAutopsia(@PathVariable Long id) { autopsiaRepository.deleteById(id); }
	 */
}
