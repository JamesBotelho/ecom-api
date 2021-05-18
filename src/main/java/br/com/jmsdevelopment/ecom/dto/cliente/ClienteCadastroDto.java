package br.com.jmsdevelopment.ecom.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class ClienteCadastroDto extends ClienteDto {
	private String senha;
}
