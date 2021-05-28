package br.com.jmsdevelopment.ecom.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.jmsdevelopment.ecom.dto.pedido.ItemPedidoDto;
import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;
import br.com.jmsdevelopment.ecom.model.ItemPedido;
import br.com.jmsdevelopment.ecom.model.Pedido;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
	@Mappings({
		@Mapping(target = "cliente.id", source = "cliente.id"),
		@Mapping(target = "cliente.nome", source = "cliente.nome"),
		@Mapping(target = "cliente.cpf", ignore = true),
		@Mapping(target = "cliente.dataNascimento", ignore = true)
	})
	PedidoDto toPedidoDto(Pedido pedido);
	
	@Mappings({
		@Mapping(target = "nomeProduto", source = "produto.nome"),
		@Mapping(target = "idProduto", source = "produto.id")
	})
	ItemPedidoDto toItemPedidoDto(ItemPedido itemPedido);
	
	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "cliente.cpf", ignore = true),
		@Mapping(target = "cliente.email", ignore = true),
		@Mapping(target = "cliente.dataNascimento", ignore = true),
		@Mapping(target = "cliente.nome", ignore = true)
	})
	Pedido toPedidoEntity(PedidoDto pedidoDto);

	@Mappings({
		@Mapping(target = "produto.id", source = "idProduto")
	})
	ItemPedido toPedidoItemEntity(ItemPedidoDto itemPedidoDto);
}
