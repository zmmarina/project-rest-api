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

import com.gft.desafioapi.controllers.dto.LegistaDTO;
import com.gft.desafioapi.entities.Legista;
import com.gft.desafioapi.events.CreatedResourceEvent;
import com.gft.desafioapi.repositories.LegistaRepository;
import com.gft.desafioapi.service.LegistaService;

@RestController
@RequestMapping("/legista")
public class LegistaController {
	
	@Autowired
	private LegistaRepository legistaRepository;
	
	@Autowired
	private LegistaService legistaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public List<LegistaDTO> listarLegistas(){
		
		List<LegistaDTO> legistaDTOLista = new ArrayList<>();
		List<Legista> legistaLista = legistaRepository.findAll();
		
		for(Legista legista : legistaLista) {
			LegistaDTO legistaDTO = LegistaDTO.from(legista);
			legistaDTOLista.add(legistaDTO);
		}
		return legistaDTOLista;
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public ResponseEntity<LegistaDTO> encontrarLegistaPorId(@PathVariable Long id){
		Optional<Legista> encontradoLegista = legistaRepository.findById(id);
		LegistaDTO legistaDTO = LegistaDTO.from(encontradoLegista);
		return !encontradoLegista.isEmpty() ? ResponseEntity.ok(legistaDTO) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<LegistaDTO> criarLegista (@Valid @RequestBody Legista legista, HttpServletResponse response){
		Legista salvoLegista = legistaRepository.save(legista);
		publisher.publishEvent(new CreatedResourceEvent(this, response, salvoLegista.getId()));
		LegistaDTO legistaDTO = LegistaDTO.from(salvoLegista);
		return ResponseEntity.status(HttpStatus.CREATED).body(legistaDTO);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<LegistaDTO> atualizarLegista(@PathVariable Long id, @Valid @RequestBody Legista legista){
		Legista salvoLegista = legistaService.atualizarLegista(id, legista);
		LegistaDTO legistaDTO = LegistaDTO.from(salvoLegista);
		return ResponseEntity.ok(legistaDTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('JUIZ')")
	public void deletarLegista(@PathVariable Long id) {
		legistaRepository.deleteById(id);
	}

}
