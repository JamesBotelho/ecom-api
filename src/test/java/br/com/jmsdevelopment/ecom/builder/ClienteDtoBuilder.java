package br.com.jmsdevelopment.ecom.builder;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;

public class ClienteDtoBuilder {
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String dataNascimento;
	
	public ClienteDtoBuilder comId(Long id) {
		this.id = id;
		return this;
	}
	
	public ClienteDtoBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public ClienteDtoBuilder comCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}
	
	public ClienteDtoBuilder comEmail(String email) {
		this.email = email;
		return this;
	}
	
	public ClienteDtoBuilder comDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
		return this;
	}
	
	public ClienteDto build() {
		return new ClienteDto(id, nome, cpf, email, dataNascimento);
	}
	
}
