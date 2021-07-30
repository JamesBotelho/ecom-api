package br.com.jmsdevelopment.ecom.config.handler;

import br.com.jmsdevelopment.ecom.dto.MensagemDto;
import br.com.jmsdevelopment.ecom.helpers.exception.CarrinhoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CarrinhoNaoEncontradoHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CarrinhoNaoEncontradoException.class)
    public MensagemDto handler(CarrinhoNaoEncontradoException exception) {
        return new MensagemDto(exception.getMessage());
    }
}
