package br.com.jmsdevelopment.ecom.helpers.exception;

public class CarrinhoNaoEncontradoException extends RuntimeException {

    private static final String MENSAGEM_PADRAO = "Carrinho não encontrado";

    public CarrinhoNaoEncontradoException() {
        super(MENSAGEM_PADRAO);
    }

    public CarrinhoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
