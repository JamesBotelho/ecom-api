package br.com.jmsdevelopment.ecom.dto.pedido;

import java.math.BigDecimal;
import java.util.List;

import br.com.jmsdevelopment.ecom.dto.cliente.ClientePedidoDto;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDto {
	private Long id;
	private BigDecimal valorTotal;
	@Valid
	@NotEmpty
	private List<ItemPedidoDto> itens;
	@Valid
	@NotNull
	private ClientePedidoDto cliente;
	private String dataHora;
}
