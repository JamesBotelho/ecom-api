package br.com.jmsdevelopment.ecom.dto.cliente;

import lombok.Data;

@Data
public class ClienteAlteraSenhDto {
	private String senhaAntiga;
	private String senhaNova;
}
