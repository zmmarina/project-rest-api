package com.gft.desafioapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailEnviarService {
	
	@Autowired
	private JavaMailSender enviadorEmail;
	
	public void enviarEmail(String destino, String corpo, String assunto) {
		
		SimpleMailMessage mensagem = new SimpleMailMessage();
		
		mensagem.setFrom("enviandoemails100@gmail.com");
		mensagem.setTo(destino);
		mensagem.setText(corpo);
		mensagem.setSubject(assunto);		
		enviadorEmail.send(mensagem);
		
	}	
	
}
