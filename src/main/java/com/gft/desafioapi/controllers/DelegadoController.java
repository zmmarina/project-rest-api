package com.gft.desafioapi.controllers;

import java.util.ArrayList;
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

import com.gft.desafioapi.controllers.dto.DelegadoDTO;
import com.gft.desafioapi.entities.Delegado;
import com.gft.desafioapi.events.CreatedResourceEvent;
import com.gft.desafioapi.repositories.DelegadoRepository;
import com.gft.desafioapi.service.DelegadoService;

@RestController
@RequestMapping("/delegado")
public class DelegadoController {
	
	@Autowired
	private DelegadoRepository delegadoRepository;
	
	@Autowired
	private DelegadoService delegadoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public ResponseEntity<List<DelegadoDTO>> listarDelegados(){
		
		List<Delegado> delegadoLista = delegadoRepository.findAll();
		List<DelegadoDTO> delegadoDTOLista = delegadoLista.stream().map(x ->DelegadoDTO.from(x)).collect(Collectors.toList());
		
		return !delegadoDTOLista.isEmpty() ? ResponseEntity.ok(delegadoDTOLista) : ResponseEntity.notFound().build();
				
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public ResponseEntity<DelegadoDTO> encontrarDelegadoPorId(@PathVariable Long id){
		Optional<Delegado> encontradoDelegado = delegadoRepository.findById(id);
		DelegadoDTO delegadoDTO = DelegadoDTO.from(encontradoDelegado);
		return !encontradoDelegado.isEmpty() ? ResponseEntity.ok(delegadoDTO) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/asc")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public List<DelegadoDTO> listarDelegadosOrdemAsc(){
		
		List<DelegadoDTO> delegadoDTOLista = new ArrayList<>();
		List<Delegado> delegadoNaOrdemAscLista = delegadoService.delegadoPorOrdemAsc();
		
		for(Delegado delegado : delegadoNaOrdemAscLista) {
			DelegadoDTO delegadoDTO = DelegadoDTO.from(delegado);
			delegadoDTOLista.add(delegadoDTO);
		}
		
		return delegadoDTOLista;
	}
	
	@GetMapping("/desc")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public List<DelegadoDTO> listarDelegadosOrdemDesc(){
		
		List<DelegadoDTO> delegadoDTOLista = new ArrayList<>();
		List<Delegado> delegadoNaOrdemDescLista = delegadoService.delegadoPorOrdemDesc();
		
		for(Delegado delegado : delegadoNaOrdemDescLista) {
			DelegadoDTO delegadoDTO = DelegadoDTO.from(delegado);
			delegadoDTOLista.add(delegadoDTO);
		}
		
		return delegadoDTOLista;
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<DelegadoDTO> criarDelegado (@Valid @RequestBody Delegado delegado, HttpServletResponse response){
		Delegado salvoDelegado = delegadoRepository.save(delegado);
		publisher.publishEvent(new CreatedResourceEvent(this, response, salvoDelegado.getId()));
		DelegadoDTO delegadoDTO = DelegadoDTO.from(salvoDelegado);
		return ResponseEntity.status(HttpStatus.CREATED).body(delegadoDTO);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<DelegadoDTO> atualizarDelegado(@PathVariable Long id, @Valid @RequestBody Delegado delegado){
		Delegado salvoDelegado = delegadoService.atualizarDelegado(id, delegado);
		DelegadoDTO delegadoDTO = DelegadoDTO.from(salvoDelegado);
		return ResponseEntity.ok(delegadoDTO);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarDelegado(@PathVariable Long id) {
		delegadoRepository.deleteById(id);
	}

}
