package br.com.jmsdevelopment.ecom.config.handler;

import br.com.jmsdevelopment.ecom.dto.erros.ErroDto;
import br.com.jmsdevelopment.ecom.helpers.exception.PedidoInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class PedidoInvalidoExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PedidoInvalidoException.class)
    public ErroDto handleException(PedidoInvalidoException exception) {
        return new ErroDto(exception.getMessage(), List.of());
    }
}
