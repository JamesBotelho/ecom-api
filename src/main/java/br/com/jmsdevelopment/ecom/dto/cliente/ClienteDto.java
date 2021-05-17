package br.com.jmsdevelopment.ecom.dto.cliente;

import lombok.Data;

@Data
public class ClienteDto {
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String dataNascimento;
}
