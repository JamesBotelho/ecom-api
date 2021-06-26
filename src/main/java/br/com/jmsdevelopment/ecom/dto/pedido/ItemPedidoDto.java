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
	@NotNull
	@Positive
	private Long idProduto;
	private String nomeProduto;
	@NotNull
	@Positive
	private Integer quantidade;
	@NotNull
	@Positive
	private BigDecimal valorProduto;
}
