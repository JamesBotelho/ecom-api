package br.com.jmsdevelopment.ecom.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteCadastroDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	Cliente fromClienteCadastroToModel(ClienteCadastroDto clienteCadastroDto);
	ClienteDto toClienteDto(Cliente cliente);
	
	@BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
	Cliente toModel(ClienteDto clienteDto, @MappingTarget Cliente cliente);
}
