package br.com.jmsdevelopment.ecom.config.handler;

import br.com.jmsdevelopment.ecom.dto.erros.ErroDto;
import br.com.jmsdevelopment.ecom.helpers.exception.PedidoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class PedidoNaoEncontradoExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PedidoNaoEncontradoException.class)
    public ErroDto handleException(PedidoNaoEncontradoException exception) {
        return new ErroDto(exception.getMessage(), List.of());
    }
}
