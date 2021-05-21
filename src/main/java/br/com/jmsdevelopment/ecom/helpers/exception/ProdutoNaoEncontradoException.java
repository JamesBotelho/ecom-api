package br.com.jmsdevelopment.ecom.helpers.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -3193953124090591451L;
	
	private static final String MENSAGEM_PADRAO = "Produto não econtrado";
	
	public ProdutoNaoEncontradoException() {
		super(MENSAGEM_PADRAO);
	}

	public ProdutoNaoEncontradoException(String message) {
		super(message);
	}
}
