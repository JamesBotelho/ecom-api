package br.com.jmsdevelopment.ecom.config.handler;

import br.com.jmsdevelopment.ecom.dto.erros.ErroDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
import java.util.List;

@RestControllerAdvice
public class DateTimeExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeException.class)
    public ErroDto handleException(DateTimeException exception) {
        return new ErroDto(exception.getMessage(), List.of());
    }
}
