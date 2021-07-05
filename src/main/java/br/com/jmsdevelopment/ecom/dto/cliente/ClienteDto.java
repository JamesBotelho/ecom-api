package br.com.jmsdevelopment.ecom.dto.cliente;

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
	private Long id;
	@NotBlank(message = "O nome não pode ser vazio")
	private String nome;
	@CPF(message = "CPF inválido")
	private String cpf;
	@NotBlank(message = "O e-mail é obrigatório")
	@Email(message = "E-mail inválido")
	private String email;
	@Pattern(regexp = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])", message = "Data de nascimento inválida")
	private String dataNascimento;
}
