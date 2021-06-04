package br.com.jmsdevelopment.ecom.helpers.exception;

public class PedidoInvalidoException extends RuntimeException {

    private static final String MENSAGEM_PADRAO = "Pedido inválido";

    public PedidoInvalidoException() {
        super(MENSAGEM_PADRAO);
    }

    public PedidoInvalidoException(String message) {
        super(message);
    }
}
