package br.com.jmsdevelopment.ecom.config.handler;

import br.com.jmsdevelopment.ecom.dto.erros.ErroDto;
import br.com.jmsdevelopment.ecom.helpers.exception.IdClienteInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class IdClienteInvalidoExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IdClienteInvalidoException.class)
    public ErroDto handler(IdClienteInvalidoException exception) {
        return new ErroDto(exception.getMessage(), List.of());
    }
}
