package br.com.jmsdevelopment.ecom.dto.categoria;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoriaDto {
	@ApiModelProperty(value = "ID da categoria")
	private Long id;
	@ApiModelProperty(value = "Nome da categoria")
	private String categoria;
}
