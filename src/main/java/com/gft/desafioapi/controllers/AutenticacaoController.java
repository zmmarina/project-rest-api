package com.gft.desafioapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.desafioapi.controllers.dto.TokenDTO;
import com.gft.desafioapi.controllers.form.AutenticacaoForm;
import com.gft.desafioapi.service.AutenticacaoService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	AutenticacaoService autenticacaoService;
		
	@PostMapping	
	public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoForm autenticacaoForm){
		
		try { 
			return ResponseEntity.ok(autenticacaoService.autenticar(autenticacaoForm));
			
		} catch(AuthenticationException ae) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();		
			}
		
		
	}

}
