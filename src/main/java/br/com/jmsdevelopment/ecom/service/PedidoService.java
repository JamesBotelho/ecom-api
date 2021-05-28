package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;

public interface PedidoService {
	PedidoDto pedidoPorId(Long id);
	PedidoDto salvaPedido(PedidoDto pedidoDto);
}
