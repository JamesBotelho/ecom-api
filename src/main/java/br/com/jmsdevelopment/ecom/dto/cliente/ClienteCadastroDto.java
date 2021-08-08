package br.com.jmsdevelopment.ecom.dto.cliente;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class ClienteCadastroDto extends ClienteDto {
	@ApiModelProperty(value = "Senha (entre 8 e 20 caracteres)", required = true)
	@NotEmpty(message = "A senha deve conter entre 8 e 20 caracteres")
	@Size(min = 8, max = 20, message = "A senha deve conter entre 8 e 20 caracteres")
	private String senha;
}
