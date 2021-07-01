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
	@NotEmpty(message = "O pedido tem que conter pelo menos 1 item")
	private List<ItemPedidoDto> itens;
	@Valid
	@NotNull(message = "É necessário informar o cliente do pedido")
	private ClientePedidoDto cliente;
	private String dataHora;
}
