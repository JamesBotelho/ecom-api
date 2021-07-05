package br.com.jmsdevelopment.ecom.dto.cliente;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ClienteAlteraSenhaDto {
	@Size(min = 8, max = 20, message = "A senha deve conter entre 8 e 10 caracteres")
	private String senhaAntiga;
	@Size(min = 8, max = 20, message = "A senha deve conter entre 8 e 10 caracteres")
	private String senhaNova;
}
