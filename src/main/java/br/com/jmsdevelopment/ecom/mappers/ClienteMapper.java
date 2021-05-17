package br.com.jmsdevelopment.ecom.mappers;

import org.mapstruct.Mapper;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteCadastroDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	Cliente fromClienteCadastroToModel(ClienteCadastroDto clienteCadastroDto);
	ClienteDto toClienteDto(Cliente cliente);
}
