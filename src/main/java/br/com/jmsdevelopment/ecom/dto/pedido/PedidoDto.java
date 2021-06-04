package br.com.jmsdevelopment.ecom.dto.pedido;

import java.math.BigDecimal;
import java.util.List;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDto {
	private Long id;
	private BigDecimal valorTotal;
	private List<ItemPedidoDto> itens;
	private ClienteDto cliente;
	private String dataHora;
}
