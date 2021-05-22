package br.com.jmsdevelopment.ecom.mappers;

import org.mapstruct.Mapper;

import br.com.jmsdevelopment.ecom.dto.categoria.CategoriaDto;
import br.com.jmsdevelopment.ecom.model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
	CategoriaDto toDto(Categoria categoria);
}
