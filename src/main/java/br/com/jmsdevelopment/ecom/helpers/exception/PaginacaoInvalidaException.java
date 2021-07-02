package br.com.jmsdevelopment.ecom.helpers.exception;

public class PaginacaoInvalidaException extends RuntimeException {

    public PaginacaoInvalidaException(String message) {
        super(message);
    }
}
