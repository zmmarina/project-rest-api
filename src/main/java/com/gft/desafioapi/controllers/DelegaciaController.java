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

import com.gft.desafioapi.controllers.dto.DelegaciaDTO;
import com.gft.desafioapi.entities.Delegacia;
import com.gft.desafioapi.events.CreatedResourceEvent;
import com.gft.desafioapi.repositories.DelegaciaRepository;
import com.gft.desafioapi.service.DelegaciaService;


@RestController
@RequestMapping("/delegacia")
public class DelegaciaController {
	
	@Autowired
	private DelegaciaRepository delegaciaRepository;
	
	@Autowired
	private DelegaciaService delegaciaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public ResponseEntity<List<DelegaciaDTO>> listarDelegacias(){
		
		List<Delegacia> delegaciaLista = delegaciaRepository.findAll();
		List<DelegaciaDTO> delegaciaDTOLista = delegaciaLista.stream().map(x -> DelegaciaDTO.from(x)).collect(Collectors.toList());
		
		return !delegaciaDTOLista.isEmpty() ? ResponseEntity.ok(delegaciaDTOLista) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public ResponseEntity<DelegaciaDTO> encontrarDelegaciaPorId(@PathVariable Long id){
		Optional<Delegacia> encontradaDelegacia = delegaciaRepository.findById(id);
		DelegaciaDTO delegaciaDTO = DelegaciaDTO.from(encontradaDelegacia);
		return !encontradaDelegacia.isEmpty() ? ResponseEntity.ok(delegaciaDTO) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<DelegaciaDTO> criarDelegacia (@Valid @RequestBody Delegacia delegacia, HttpServletResponse response){
		Delegacia salvaDelegacia = delegaciaRepository.save(delegacia);
		publisher.publishEvent(new CreatedResourceEvent(this, response, salvaDelegacia.getId()));
		DelegaciaDTO delegaciaDTO = DelegaciaDTO.from(salvaDelegacia);
		return ResponseEntity.status(HttpStatus.CREATED).body(delegaciaDTO);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<DelegaciaDTO> atualizarDelegacia(@PathVariable Long id, @Valid @RequestBody Delegacia delegacia){
		Delegacia salvaDelegacia = delegaciaService.atualizarDelegacia(id, delegacia);
		DelegaciaDTO delegaciaDTO = DelegaciaDTO.from(salvaDelegacia);
		return ResponseEntity.ok(delegaciaDTO);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarDelegacia(@PathVariable Long id) {
		delegaciaRepository.deleteById(id);
	}


}
