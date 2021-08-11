package br.com.jmsdevelopment.ecom.config.handler;

import br.com.jmsdevelopment.ecom.dto.erros.ErroDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.jmsdevelopment.ecom.helpers.exception.CategoriaNaoEncontradaException;

import java.util.List;

@RestControllerAdvice
public class CategoriaNaoEncontradaHandler {
	
	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErroDto handleException(CategoriaNaoEncontradaException exception) {
		return new ErroDto(exception.getMessage(), List.of());
	}
}
