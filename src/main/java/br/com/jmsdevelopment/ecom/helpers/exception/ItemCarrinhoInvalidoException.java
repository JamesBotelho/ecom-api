package br.com.jmsdevelopment.ecom.helpers.exception;

public class ItemCarrinhoInvalidoException extends RuntimeException {

    private static final String MENSAGEM_PADRAO = "Há itens inválidos no seu carrinho";

    public ItemCarrinhoInvalidoException() {
        super(MENSAGEM_PADRAO);
    }

    public ItemCarrinhoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
