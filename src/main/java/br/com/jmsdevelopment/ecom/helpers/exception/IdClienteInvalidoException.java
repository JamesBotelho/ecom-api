package br.com.jmsdevelopment.ecom.helpers.exception;

public class IdClienteInvalidoException extends RuntimeException {

    private static final String MENSAGEM_PADRAO = "O cliente requisitado não é o mesmo que está logado!";

    public IdClienteInvalidoException() {
        super(MENSAGEM_PADRAO);
    }

    public IdClienteInvalidoException(String mensagem) {
        super(mensagem);
    }
}
