package com.gft.desafioapi.events.listeners;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.desafioapi.events.CreatedResourceEvent;

@Component
public class CreatedResourceListener implements ApplicationListener<CreatedResourceEvent>{

	@Override
	public void onApplicationEvent(CreatedResourceEvent createdResourceEvent) {
		HttpServletResponse response = createdResourceEvent.getResponse();		
		Long id = createdResourceEvent.getId();
		
		addHeaderLocation(response, id);		
	}

	private void addHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(id).toUri();
		
		response.setHeader("Location", uri.toASCIIString());
	}	
	
}