package br.com.jmsdevelopment.ecom.dto.cliente;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClienteCadastroDto extends ClienteDto {
	private String senha;
}
