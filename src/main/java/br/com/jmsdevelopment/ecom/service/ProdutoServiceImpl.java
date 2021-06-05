package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import br.com.jmsdevelopment.ecom.helpers.exception.ProdutoNaoEncontradoException;
import br.com.jmsdevelopment.ecom.mappers.ProdutoMapper;
import br.com.jmsdevelopment.ecom.model.Produto;
import br.com.jmsdevelopment.ecom.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	private final ProdutoRepository produtoRepository;
	private final ProdutoMapper produtoMapper;
	
	@Override
	public Page<ProdutoDto> todosOsProdutos(Pageable pageable) {
		Page<Produto> produtoEntityPaginado = produtoRepository.findAll(pageable);

		if (produtoEntityPaginado.isEmpty()) {
			throw new ProdutoNaoEncontradoException("Não há produtos cadastrados");
		}

		return produtoEntityPaginado.map(produtoMapper::toDto);
	}
	
	@Override
	public ProdutoDto recuperaProdutoPorId(Long id) {
		Produto produtoRetornado = produtoRepository.findById(id).orElseThrow(ProdutoNaoEncontradoException::new);
		return produtoMapper.toDto(produtoRetornado);
	}

	@Override
	public Page<ProdutoDto> produtosPorCategoria(Long id, Pageable pageable) {
		Page<Produto> produtosRetornados = produtoRepository.findByCategoriaId(id, pageable);

		if (produtosRetornados.isEmpty()) {
			throw new ProdutoNaoEncontradoException("Não há produtos cadastrados nesta categoria");
		}

		return produtosRetornados.map(produtoMapper::toDto);
	}
}
