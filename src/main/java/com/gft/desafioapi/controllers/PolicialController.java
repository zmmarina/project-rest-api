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

import com.gft.desafioapi.controllers.dto.PolicialDTO;
import com.gft.desafioapi.entities.Policial;
import com.gft.desafioapi.events.CreatedResourceEvent;
import com.gft.desafioapi.repositories.PolicialRepository;
import com.gft.desafioapi.repositories.filter.PolicialFilter;
import com.gft.desafioapi.service.PolicialService;

@RestController
@RequestMapping("/policial")
public class PolicialController {
	
	@Autowired
	private PolicialRepository policialRepository;
	
	@Autowired
	private PolicialService policialService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public ResponseEntity<List<PolicialDTO>> listarPoliciais(){
		
		List<Policial> policialLista = policialRepository.findAll();
		List<PolicialDTO> policialDTOLista = policialLista.stream().map(x -> PolicialDTO.from(x)).collect(Collectors.toList());
		
		return !policialDTOLista.isEmpty() ? ResponseEntity.ok(policialDTOLista) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO')")
	public ResponseEntity<PolicialDTO> encontrarPolicialPorId(@PathVariable Long id){
		Optional<Policial> encontradoPolicial = policialRepository.findById(id);
		PolicialDTO policialDTO = PolicialDTO.from(encontradoPolicial);
		return !encontradoPolicial.isEmpty() ? ResponseEntity.ok(policialDTO) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/nome/{nome}")
	@PreAuthorize("hasAuthority('JUIZ') and hasAuthority('ADVOGADO')")
	public List<PolicialDTO> listarPoliciaisNome(PolicialFilter policialFilter){
		
		List<PolicialDTO> policialDTOLista = new ArrayList<>();
		List<Policial> policialLista = policialRepository.policialFiltrarPorNome(policialFilter);
		
		for(Policial policial : policialLista) {
			PolicialDTO policialDTO = PolicialDTO.from(policial);
			policialDTOLista.add(policialDTO);
		}
		return policialDTOLista;
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<PolicialDTO> criarPolicial (@Valid @RequestBody Policial policial, HttpServletResponse response){
		Policial salvoPolicial = policialRepository.save(policial);
		publisher.publishEvent(new CreatedResourceEvent(this, response, salvoPolicial.getId()));
		PolicialDTO policialDTO = PolicialDTO.from(salvoPolicial);
		return ResponseEntity.status(HttpStatus.CREATED).body(policialDTO);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ')")
	public ResponseEntity<PolicialDTO> atualizarPolicial(@PathVariable Long id, @Valid @RequestBody Policial policial){
		Policial salvoPolicial = policialService.atualizarPolicial(id, policial);
		PolicialDTO policialDTO = PolicialDTO.from(salvoPolicial);
		return ResponseEntity.ok(policialDTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('JUIZ')")
	public void deletarPolicial(@PathVariable Long id) {
		policialRepository.deleteById(id);
	}


}
