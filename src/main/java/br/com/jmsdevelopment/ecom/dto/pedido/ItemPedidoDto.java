package br.com.jmsdevelopment.ecom.dto.pedido;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedidoDto {
	@NotNull(message = "É necessário informar o id do produto")
	@Positive(message = "ID do produto inválido")
	@ApiModelProperty(value = "ID do produto", required = true)
	private Long idProduto;
	@ApiModelProperty(value = "Nome do produto")
	private String nomeProduto;
	@NotNull(message = "É necessário informar a quantidade do produto")
	@Positive(message = "A quantidade de itens deve ser maior que zero")
	@ApiModelProperty(value = "Quantidade do produto", required = true)
	private Integer quantidade;
	@NotNull(message = "Valor do produto inválido")
	@Positive(message = "Valor do produto inválido")
	@ApiModelProperty(value = "Valor do produto", required = true)
	private BigDecimal valorProduto;
}
