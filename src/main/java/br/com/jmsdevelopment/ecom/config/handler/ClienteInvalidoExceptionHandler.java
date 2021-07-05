package br.com.jmsdevelopment.ecom.config.handler;

import br.com.jmsdevelopment.ecom.dto.MensagemDto;
import br.com.jmsdevelopment.ecom.helpers.exception.ClienteInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClienteInvalidoExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ClienteInvalidoException.class)
    public MensagemDto handleException(ClienteInvalidoException e) {
        return new MensagemDto(e.getMessage());
    }
}
