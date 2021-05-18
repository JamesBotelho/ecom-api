package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;

public interface ProdutoService {
	ProdutoDto todosOsProdutos();
	ProdutoDto recuperaProdutoPorId(Long id);
}
