package br.com.jmsdevelopment.ecom.helpers.exception;

public class TokenInvalidoException extends RuntimeException {

    private static final String MENSAGEM_PADRAO = "Token inválido";

    public TokenInvalidoException() {
        super(MENSAGEM_PADRAO);
    }

    public TokenInvalidoException(String mensagem) {
        super(mensagem);
    }
}
