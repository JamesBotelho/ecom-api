package br.com.jmsdevelopment.ecom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import br.com.jmsdevelopment.ecom.mappers.ProdutoMapper;
import br.com.jmsdevelopment.ecom.model.Produto;
import br.com.jmsdevelopment.ecom.repository.ProdutoRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProdutoServiceImpl {
	
	private final ProdutoRepository produtoRepository;
	private final ProdutoMapper produtoMapper;
	
	public List<ProdutoDto> todosOsProdutos() {
		return produtoRepository.findAll().stream().map(produtoMapper::toDto).collect(Collectors.toList());
	}
	
	public ProdutoDto produtoPorId(Long id) {
		Produto produtoRetornado = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		return produtoMapper.toDto(produtoRetornado);
	}
}
