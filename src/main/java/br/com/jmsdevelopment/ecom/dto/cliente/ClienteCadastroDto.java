package br.com.jmsdevelopment.ecom.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class ClienteCadastroDto extends ClienteDto {
	@Size(min = 8, max = 20, message = "A senha deve conter entre 8 e 10 caracteres")
	private String senha;
}
