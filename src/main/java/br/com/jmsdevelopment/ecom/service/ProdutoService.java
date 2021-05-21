package br.com.jmsdevelopment.ecom.service;

import java.util.List;

import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;

public interface ProdutoService {
	List<ProdutoDto> todosOsProdutos();
	ProdutoDto recuperaProdutoPorId(Long id);
}
