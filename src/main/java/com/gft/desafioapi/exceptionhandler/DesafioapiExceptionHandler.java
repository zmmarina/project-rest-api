package com.gft.desafioapi.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DesafioapiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String userMsg = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());		
		String devMsg = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		
		List<Erro> errors = Arrays.asList(new Erro(userMsg, devMsg));
		
		return handleExceptionInternal(ex, errors , headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> errors = createErroList(ex.getBindingResult());
		
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		
		String userMsg = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());		
		String devMsg = ex.toString();
		
		List<Erro> errors = Arrays.asList(new Erro(userMsg, devMsg));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ NoSuchElementException.class })
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
		
		String userMsg = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());		
		String devMsg = ex.toString();
		
		List<Erro> errors = Arrays.asList(new Erro(userMsg, devMsg));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
		
		String userMsg = messageSource.getMessage("resource.forbidden-operation", null, LocaleContextHolder.getLocale());		
		String devMsg = ExceptionUtils.getRootCauseMessage(ex);
		
		List<Erro> errors = Arrays.asList(new Erro(userMsg, devMsg));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	private List<Erro> createErroList(BindingResult bindingResult){
		
		List<Erro> errors = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			
			String userMsg = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String devMsg = fieldError.toString();
			
			errors.add(new Erro(userMsg, devMsg));
		}
		
		return errors;
	}
	
	public static class Erro { 
		
		private String userMsg;
		private String devMsg;
		
		
		public Erro (String userMsg, String devMsg) {
			this.userMsg = userMsg;
			this.devMsg = devMsg;
		}


		public String getUserMsg() {
			return userMsg;
		}

		public String getDevMsg() {
			return devMsg;
		}		
		
	}

}
