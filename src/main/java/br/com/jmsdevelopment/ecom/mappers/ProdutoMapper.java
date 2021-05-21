package br.com.jmsdevelopment.ecom.mappers;

import org.mapstruct.Mapper;

import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import br.com.jmsdevelopment.ecom.model.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
	ProdutoDto toDto(Produto produto);
}
