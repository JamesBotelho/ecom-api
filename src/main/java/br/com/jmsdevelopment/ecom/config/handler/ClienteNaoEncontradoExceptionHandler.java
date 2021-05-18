package br.com.jmsdevelopment.ecom.config.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.jmsdevelopment.ecom.dto.MensagemDto;
import br.com.jmsdevelopment.ecom.helpers.exception.ClienteNaoEncontradoException;

@RestControllerAdvice
public class ClienteNaoEncontradoExceptionHandler {
	
	@ExceptionHandler(ClienteNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public MensagemDto handleException(ClienteNaoEncontradoException exception) {
		return new MensagemDto(exception.getMessage());
	}
	
}
