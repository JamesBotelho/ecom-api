package br.com.jmsdevelopment.ecom.dto.produto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoDto {
	@ApiModelProperty(value = "ID do produto")
	private Long id;
	@ApiModelProperty(value = "Nome do produto")
	private String nome;
	@ApiModelProperty(value = "Descrição do produto")
	private String descricao;
	@ApiModelProperty(value = "URL contendo a imagem do produto")
	private String urlImagem;
	@ApiModelProperty(value = "Preço do produto")
	private BigDecimal preco;
	@ApiModelProperty(value = "Preço promocional do produto")
	private BigDecimal precoPromocional;
}
