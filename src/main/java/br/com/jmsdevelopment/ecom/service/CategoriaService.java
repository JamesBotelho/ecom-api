package br.com.jmsdevelopment.ecom.service;

import java.util.List;

import br.com.jmsdevelopment.ecom.dto.categoria.CategoriaDto;
import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;

public interface CategoriaService {
	List<CategoriaDto> todasAsCategorias();
	CategoriaDto categoriaPorId(Long id);
	List<ProdutoDto> produtosDaCategoria(Long id);
}
