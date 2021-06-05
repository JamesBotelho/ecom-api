package br.com.jmsdevelopment.ecom.service;

import java.util.List;

import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {
	List<ProdutoDto> todosOsProdutos();
	ProdutoDto recuperaProdutoPorId(Long id);
	Page<ProdutoDto> produtosPorCategoria(Long id, Pageable pageable);
}
