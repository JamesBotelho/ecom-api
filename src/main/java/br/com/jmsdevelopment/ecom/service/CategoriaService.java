package br.com.jmsdevelopment.ecom.service;

import java.util.List;

import br.com.jmsdevelopment.ecom.dto.categoria.CategoriaDto;

public interface CategoriaService {
	List<CategoriaDto> todasAsCategorias();
	CategoriaDto categoriaPorId(Long id);
}
