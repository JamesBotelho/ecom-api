package br.com.jmsdevelopment.ecom.config.handler;

import br.com.jmsdevelopment.ecom.dto.erros.ErroDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.jmsdevelopment.ecom.helpers.exception.ProdutoNaoEncontradoException;

import java.util.List;

@RestControllerAdvice
public class ProdutoNaoEncontradoExceptionHandler {
	
	@ExceptionHandler(ProdutoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErroDto handlException(ProdutoNaoEncontradoException exception) {
		return new ErroDto(exception.getMessage(), List.of());
	}
}
