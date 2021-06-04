package br.com.jmsdevelopment.ecom.dto.pedido;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedidoDto {
	private Long idProduto;
	private String nomeProduto;
	private Integer quantidade;
	private BigDecimal valorProduto;
}
