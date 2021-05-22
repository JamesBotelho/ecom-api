package br.com.jmsdevelopment.ecom.config.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.jmsdevelopment.ecom.dto.MensagemDto;
import br.com.jmsdevelopment.ecom.helpers.exception.CategoriaNaoEncontradaException;

@RestControllerAdvice
public class CategoriaNaoEncontradaHandler {
	
	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public MensagemDto handleException(CategoriaNaoEncontradaException exception) {
		return new MensagemDto(exception.getMessage());
	}
}
