package br.com.jmsdevelopment.ecom.dto.cliente;

import lombok.Data;

@Data
public class ClienteAlteraSenhaDto {
	private String senhaAntiga;
	private String senhaNova;
}
