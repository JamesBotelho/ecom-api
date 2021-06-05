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
import br.com.jmsdevelopment.ecom.dto.categoria.CategoriaDto;
import br.com.jmsdevelopment.ecom.helpers.exception.CategoriaNaoEncontradaException;
import br.com.jmsdevelopment.ecom.helpers.exception.ProdutoNaoEncontradoException;
import br.com.jmsdevelopment.ecom.mappers.CategoriaMapper;
import br.com.jmsdevelopment.ecom.mappers.ProdutoMapper;
import br.com.jmsdevelopment.ecom.model.Categoria;
import br.com.jmsdevelopment.ecom.model.Produto;
import br.com.jmsdevelopment.ecom.repository.CategoriaRepository;

class CategoriaServiceImplMockTest {

	@Mock
	private CategoriaRepository categoriaRepository;
	
	@Mock
	private CategoriaMapper categoriaMapper;
	
	@Mock
	private ProdutoMapper produtoMapper;
	
	private CategoriaService categoriaService;
	
	private Categoria categoria;
	private Categoria categoriaSpy;
	private CategoriaDto categoriaDto;
	
	private Produto produto;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.categoriaService = new CategoriaServiceImpl(categoriaRepository, categoriaMapper, produtoMapper);
		this.categoria = new Categoria(1L, "Teste", Collections.emptyList());
		this.categoriaDto = new CategoriaDto(1L, "Teste");
		this.produto = new ProdutoBuilder()
				.comId(1L)
				.comNome("Produto")
				.comDescricao("Descrição")
				.comUrlImagem("http://url_imagem")
				.comPreco(new BigDecimal(50))
				.build();
		categoriaSpy = Mockito.spy(categoria);
		Mockito.when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
		Mockito.when(categoriaMapper.toDto(categoria)).thenReturn(categoriaDto);
	}
	
	@Test
	public void deve_RetornarCategoriaValida_QuandoPesquisaPorIdExistente() {
		CategoriaDto categoriaRetornada = categoriaService.categoriaPorId(1L);
		
		assertEquals(categoriaDto, categoriaRetornada);
	}

	@Test
	public void deve_RetornarListaDeCategorias_QuandoListaTodasCategorias() {
		Mockito.when(categoriaRepository.findAll()).thenReturn(Collections.singletonList(categoria));
		
		List<CategoriaDto> categoriasRetornadas = categoriaService.todasAsCategorias();
		
		assertEquals(1, categoriasRetornadas.size());
		assertEquals(categoriaDto, categoriasRetornadas.get(0));
	}
	
	@Test
	public void deve_RetornarCategoriaNaoEncontradaException_QuandoPesquisaPorIdInexistente() {
		Mockito.when(categoriaRepository.findById(2L)).thenReturn(Optional.empty());
		assertThrows(CategoriaNaoEncontradaException.class, () -> categoriaService.categoriaPorId(2L));
	}
	
	@Test
	public void deve_RetornarCategoriaNaoEncontradaException_QuandoNaoHaCategoriasCadastradas() {
		Mockito.when(categoriaRepository.findAll()).thenReturn(Collections.emptyList());
		assertThrows(CategoriaNaoEncontradaException.class, () -> categoriaService.todasAsCategorias());
	}

}
