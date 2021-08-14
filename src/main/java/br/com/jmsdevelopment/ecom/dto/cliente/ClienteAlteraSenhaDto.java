package br.com.jmsdevelopment.ecom.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteAlteraSenhaDto {
	@Size(min = 8, max = 20, message = "A senha deve conter entre 8 e 10 caracteres")
	private String senhaAntiga;
	@Size(min = 8, max = 20, message = "A senha deve conter entre 8 e 10 caracteres")
	private String senhaNova;
}
