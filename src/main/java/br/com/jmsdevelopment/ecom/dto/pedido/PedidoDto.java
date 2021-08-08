package br.com.jmsdevelopment.ecom.dto.pedido;

import java.math.BigDecimal;
import java.util.List;

import br.com.jmsdevelopment.ecom.dto.cliente.ClientePedidoDto;
import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ID do pedido")
	private Long id;
	@ApiModelProperty(value = "Valor total do pedido")
	private BigDecimal valorTotal;
	@Valid
	@NotEmpty(message = "O pedido tem que conter pelo menos 1 item")
	@ApiModelProperty(value = "Itens do pedido", required = true)
	private List<ItemPedidoDto> itens;
	@Valid
	@NotNull(message = "É necessário informar o cliente do pedido")
	@ApiModelProperty(value = "Cliente do pedido", required = true)
	private ClientePedidoDto cliente;
	@ApiModelProperty(value = "Data e hora em que o pedido foi gerado")
	private String dataHora;
}
