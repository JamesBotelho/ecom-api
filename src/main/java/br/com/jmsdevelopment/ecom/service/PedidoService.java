package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoService {
	PedidoDto pedidoPorId(Long id);
	PedidoDto salvaPedido(PedidoDto pedidoDto);
	Page<PedidoDto> pedidosDoCliente(Long idCliente, Pageable pageable);
}
