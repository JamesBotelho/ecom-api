package br.com.jmsdevelopment.ecom.helpers.exception;

public class CategoriaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = -663239906550594683L;
	
	private static final String MENSAGEM_PADRAO = "Categoria não encontrada";

	public CategoriaNaoEncontradaException() {
		super(MENSAGEM_PADRAO);
	}

	public CategoriaNaoEncontradaException(String message) {
		super(message);
	}
	
	
}
