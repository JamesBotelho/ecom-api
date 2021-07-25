package br.com.jmsdevelopment.ecom.builder;

import java.time.LocalDate;
import java.util.Collections;

import br.com.jmsdevelopment.ecom.model.Cliente;

public class ClienteBuilder {
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String dataNascimento;
	private String senha;
	
	public ClienteBuilder() {
		
	}
	
	public ClienteBuilder comId(Long id) {
		this.id = id;
		return this;
	}
	
	public ClienteBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public ClienteBuilder comCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}
	
	public ClienteBuilder comEmail(String email) {
		this.email = email;
		return this;
	}
	
	public ClienteBuilder comDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
		return this;
	}
	
	public ClienteBuilder comSenha(String senha) {
		this.senha = senha;
		return this;
	}
	
	public Cliente build() {
		LocalDate local = LocalDate.parse(this.dataNascimento);
		return new Cliente(id, nome, cpf, email, local, senha, Collections.emptyList());
	}
}
