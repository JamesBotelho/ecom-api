package br.com.jmsdevelopment.ecom.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MensagemDto {
	@ApiModelProperty(value = "Mensagem de retorno")
	private String mensagem;
}
