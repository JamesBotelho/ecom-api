package br.com.jmsdevelopment.ecom.dto.pedido;

import java.math.BigDecimal;

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
	private Long idProduto;
	private String nomeProduto;
	@NotNull(message = "É necessário informar a quantidade do produto")
	@Positive(message = "A quantidade de itens deve ser maior que zero")
	private Integer quantidade;
	@NotNull(message = "Valor do produto inválido")
	@Positive(message = "Valor do produto inválido")
	private BigDecimal valorProduto;
}
