package br.com.jmsdevelopment.ecom.config.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.jmsdevelopment.ecom.dto.MensagemDto;
import br.com.jmsdevelopment.ecom.helpers.exception.ProdutoNaoEncontradoException;

@RestControllerAdvice
public class ProdutoNaoEncontradoExceptionHandler {
	
	@ExceptionHandler(ProdutoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public MensagemDto handlException(ProdutoNaoEncontradoException exception) {
		return new MensagemDto(exception.getMessage());
	}
}
