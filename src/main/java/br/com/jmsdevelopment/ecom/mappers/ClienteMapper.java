package br.com.jmsdevelopment.ecom.mappers;

import org.mapstruct.*;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteCadastroDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	@Mappings({
		@Mapping(target = "id", ignore = true)
	})
	Cliente fromClienteCadastroToModel(ClienteCadastroDto clienteCadastroDto);
	ClienteDto toClienteDto(Cliente cliente);

	@Mappings({
		@Mapping(target = "email", ignore = true),
		@Mapping(target = "cpf", ignore = true),
		@Mapping(target = "id", ignore = true)
	})
	@BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
	Cliente toModel(ClienteDto clienteDto, @MappingTarget Cliente cliente);
}
