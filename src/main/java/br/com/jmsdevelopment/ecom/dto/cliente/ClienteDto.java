package br.com.jmsdevelopment.ecom.dto.cliente;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDto {
	@ApiModelProperty(value = "ID do cliente")
	private Long id;
	@ApiModelProperty(value = "Nome do cliente", required = true)
	@NotBlank(message = "O nome não pode ser vazio")
	private String nome;
	@ApiModelProperty(value = "CPF do cliente", required = true)
	@CPF(message = "CPF inválido")
	private String cpf;
	@ApiModelProperty(value = "e-mail do cliente", required = true)
	@NotBlank(message = "O e-mail é obrigatório")
	@Email(message = "E-mail inválido")
	private String email;
	@ApiModelProperty(value = "Data de nascimento do cliente (formato YYYY-MM-DD)", required = true)
	@Pattern(regexp = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])", message = "Data de nascimento inválida")
	private String dataNascimento;
}
