package br.com.jmsdevelopment.ecom.helpers.exception;

public class ClienteNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -8711942898915637108L;
	private static final String MENSAGEM_PADRAO = "Cliente não encontrado";
	
	public ClienteNaoEncontradoException() {
		super(MENSAGEM_PADRAO);
	}
	
}
