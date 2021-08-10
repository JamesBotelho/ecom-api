package br.com.jmsdevelopment.ecom.dto.erros;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MensagemDto {
	@ApiModelProperty(value = "Mensagem de retorno")
	private String mensagem;
}
