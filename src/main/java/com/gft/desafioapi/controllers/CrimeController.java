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

import com.gft.desafioapi.controllers.dto.CrimeDTO;
import com.gft.desafioapi.entities.Crime;
import com.gft.desafioapi.events.CreatedResourceEvent;
import com.gft.desafioapi.repositories.CrimeRepository;
import com.gft.desafioapi.service.CrimeService;

@RestController
@RequestMapping("/crime")
public class CrimeController {
	
	@Autowired
	private CrimeRepository crimeRepository;
	
	@Autowired
	private CrimeService crimeService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public ResponseEntity<List<CrimeDTO>> listarCrimes(){
		
		List<Crime> crimeLista = crimeRepository.findAll();
		List<CrimeDTO> crimeDTOLista = crimeLista.stream().map(x -> CrimeDTO.from(x)).collect(Collectors.toList());
		
		return !crimeDTOLista.isEmpty() ? ResponseEntity.ok(crimeDTOLista) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public ResponseEntity<CrimeDTO> encontrarCrimePorId(@PathVariable Long id){
		Optional<Crime> encontradoCrime = crimeRepository.findById(id);
		CrimeDTO crimeDTO = CrimeDTO.from(encontradoCrime);
		return !encontradoCrime.isEmpty() ? ResponseEntity.ok(crimeDTO) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<CrimeDTO> criarCrime (@Valid @RequestBody Crime crime, HttpServletResponse response){
		Crime salvoCrime = crimeRepository.save(crime);
		publisher.publishEvent(new CreatedResourceEvent(this, response, salvoCrime.getId()));
		CrimeDTO crimeDTO = CrimeDTO.from(salvoCrime);
		return ResponseEntity.status(HttpStatus.CREATED).body(crimeDTO);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<CrimeDTO> atualizarCrime(@PathVariable Long id, @Valid @RequestBody Crime crime){
		Crime salvoCrime = crimeService.atualizarCrime(id, crime);
		CrimeDTO crimeDTO = CrimeDTO.from(salvoCrime);
		return ResponseEntity.ok(crimeDTO);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCrime(@PathVariable Long id) {
		crimeRepository.deleteById(id);
	}


}
