package com.gft.desafioapi.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.gft.desafioapi.controllers.dto.VitimaDTO;
import com.gft.desafioapi.entities.Vitima;
import com.gft.desafioapi.events.CreatedResourceEvent;
import com.gft.desafioapi.repositories.VitimaRepository;
import com.gft.desafioapi.service.VitimaService;

@RestController
@RequestMapping("/vitima")
public class VitimaController {
	
	@Autowired
	private VitimaRepository vitimaRepository;
	
	@Autowired
	private VitimaService vitimaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public ResponseEntity<List<VitimaDTO>> listarVitimas(){
		
		List<Vitima> vitimaLista = vitimaRepository.findAll();
		List<VitimaDTO> vitimaDTOLista = vitimaLista.stream().map(x -> VitimaDTO.from(x)).collect(Collectors.toList());
		
		return !vitimaDTOLista.isEmpty() ? ResponseEntity.ok(vitimaDTOLista) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public ResponseEntity<VitimaDTO> encontrarVitimaPorId(@PathVariable Long id){
		Optional<Vitima> encontradaVitima = vitimaRepository.findById(id);
		VitimaDTO vitimaDTO = VitimaDTO.from(encontradaVitima);
		return !encontradaVitima.isEmpty() ? ResponseEntity.ok(vitimaDTO) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<VitimaDTO> criarVitima (@Valid @RequestBody Vitima vitima, HttpServletResponse response){
		Vitima salvaVitima = vitimaRepository.save(vitima);
		publisher.publishEvent(new CreatedResourceEvent(this, response, salvaVitima.getId()));
		VitimaDTO vitimaDTO = VitimaDTO.from(salvaVitima);
		return ResponseEntity.status(HttpStatus.CREATED).body(vitimaDTO);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<VitimaDTO> atualizarVitima(@PathVariable Long id, @Valid @RequestBody Vitima vitima){
		Vitima salvaVitima = vitimaService.atualizarVitima(id, vitima);
		VitimaDTO vitimaDTO = VitimaDTO.from(salvaVitima);
		return ResponseEntity.ok(vitimaDTO);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarVitima(@PathVariable Long id) {
		vitimaRepository.deleteById(id);
	}

	
}
