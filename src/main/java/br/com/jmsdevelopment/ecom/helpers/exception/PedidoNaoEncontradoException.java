package br.com.jmsdevelopment.ecom.helpers.exception;

public class PedidoNaoEncontradoException extends RuntimeException{

    private static final String MENSAGEM_PADRAO = "Pedido não encontrado";

    public PedidoNaoEncontradoException() {
        super(MENSAGEM_PADRAO);
    }

    public PedidoNaoEncontradoException(String message) {
        super(message);
    }
}
