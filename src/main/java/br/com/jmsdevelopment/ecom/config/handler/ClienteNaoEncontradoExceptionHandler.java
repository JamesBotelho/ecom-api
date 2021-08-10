package br.com.jmsdevelopment.ecom.config.handler;

import br.com.jmsdevelopment.ecom.dto.erros.ErroDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.jmsdevelopment.ecom.helpers.exception.ClienteNaoEncontradoException;

import java.util.List;

@RestControllerAdvice
public class ClienteNaoEncontradoExceptionHandler {
	
	@ExceptionHandler(ClienteNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErroDto handleException(ClienteNaoEncontradoException exception) {
		return new ErroDto(exception.getMessage(), List.of());
	}
	
}
