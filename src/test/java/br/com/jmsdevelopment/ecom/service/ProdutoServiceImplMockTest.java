package br.com.jmsdevelopment.ecom.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.jmsdevelopment.ecom.builder.ProdutoBuilder;
import br.com.jmsdevelopment.ecom.builder.ProdutoDtoBuilder;
import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import br.com.jmsdevelopment.ecom.helpers.exception.ProdutoNaoEncontradoException;
import br.com.jmsdevelopment.ecom.mappers.ProdutoMapper;
import br.com.jmsdevelopment.ecom.model.Produto;
import br.com.jmsdevelopment.ecom.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

class ProdutoServiceImplMockTest {

	@Mock
	private ProdutoRepository produtoRepository;
	
	@Mock
	private ProdutoMapper produtoMapper;
	
	private ProdutoService produtoService;
	
	private Produto produto;
	
	private ProdutoDto produtoDto;
	
	private List<Produto> produtosEntity;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		produto = new ProdutoBuilder()
				.comId(1L)
				.comNome("Caixa de fósforo")
				.comDescricao("Uma caixa de fósforo que faz o fogo aparecer")
				.comUrlImagem("https://www.imagemaqui.com.br")
				.comPreco(new BigDecimal("200"))
				.comPrecoPromocional(null)
				.build();
		
		produtoDto = new ProdutoDtoBuilder()
				.comId(1L)
				.comNome("Caixa de fósforo")
				.comDescricao("Uma caixa de fósforo que faz o fogo aparecer")
				.comUrlImagem("https://www.imagemaqui.com.br")
				.comPreco(new BigDecimal("200"))
				.comPrecoPromocional(null)
				.build();
		produtosEntity = Collections.singletonList(produto);
		Mockito.when(produtoRepository.findAll()).thenReturn(produtosEntity);
		
		produtoService = new ProdutoServiceImpl(produtoRepository, produtoMapper);
	}

	@Test
	public void deve_ChamarListagemDoBanco_QuandoListaTodosProdutos() {
		Mockito.when(produtoRepository.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(Collections.singletonList(produto)));
		produtoService.todosOsProdutos(Pageable.unpaged());
		Mockito.verify(produtoRepository).findAll(Pageable.unpaged());
	}
	
	@Test
	public void deve_retornarUmProduto_QuandoRecuperaProdutoPorId() {
		Mockito.when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
		Mockito.when(produtoMapper.toDto(produto)).thenReturn(produtoDto);
		
		ProdutoDto produtoRetornado = produtoService.recuperaProdutoPorId(1L);
		
		assertEquals(produtoDto, produtoRetornado);
	}
	
	@Test
	public void deve_lancarProdutoNaoEncontradoException_QuandoListaProdutosSemTerProdutosCadastrado() {
		Mockito.when(produtoRepository.findAll(Pageable.unpaged())).thenReturn(Page.empty());
		
		assertThrows(ProdutoNaoEncontradoException.class, () -> produtoService.todosOsProdutos(Pageable.unpaged()));
	}
	
	@Test
	public void deve_lancarProdutoNaoEncontradoException_QuandoRecuperaProdutoComIdInexistente() {
		Mockito.when(produtoRepository.findById(2L)).thenReturn(Optional.empty());
		
		assertThrows(ProdutoNaoEncontradoException.class, () -> produtoService.recuperaProdutoPorId(2L));
	}

	@Test
	public void deve_RetornarUmProduto_QuandoPesquisaPorCategoria() {
		Mockito.when(produtoRepository.findByCategoriaId(1L, Pageable.unpaged())).thenReturn(new PageImpl<>(Collections.singletonList(produto)));
		Mockito.when(produtoMapper.toDto(produto)).thenReturn(produtoDto);

		Page<ProdutoDto> produtosRetornados = produtoService.produtosPorCategoria(1L, Pageable.unpaged());

		assertEquals(1, produtosRetornados.getTotalElements());
		assertEquals(1, produtosRetornados.getTotalPages());

		ProdutoDto produtoRetornado = produtosRetornados.getContent().get(0);

		assertEquals(produtoDto, produtoRetornado);
	}

	@Test
	public void deve_lancarProdutoNaoEncontradoExecption_QuandoNaoHaProdutosCadastradosNaCategoria() {
		Mockito.when(produtoRepository.findByCategoriaId(1L, Pageable.unpaged())).thenReturn(Page.empty());

		assertThrows(ProdutoNaoEncontradoException.class, () -> produtoService.produtosPorCategoria(1L, Pageable.unpaged()));
	}
}
