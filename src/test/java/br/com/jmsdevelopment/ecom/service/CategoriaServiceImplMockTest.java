package br.com.jmsdevelopment.ecom.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.jmsdevelopment.ecom.dto.categoria.CategoriaDto;
import br.com.jmsdevelopment.ecom.helpers.exception.CategoriaNaoEncontradaException;
import br.com.jmsdevelopment.ecom.mappers.CategoriaMapper;
import br.com.jmsdevelopment.ecom.model.Categoria;
import br.com.jmsdevelopment.ecom.repository.CategoriaRepository;

class CategoriaServiceImplMockTest {

	@Mock
	private CategoriaRepository categoriaRepository;
	
	@Mock
	private CategoriaMapper categoriaMapper;
	
	private CategoriaService categoriaService;
	
	private Categoria categoria;
	private CategoriaDto categoriaDto;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.categoriaService = new CategoriaServiceImpl(categoriaRepository, categoriaMapper);
		this.categoria = new Categoria(1L, "Teste", Arrays.asList());
		this.categoriaDto = new CategoriaDto(1L, "Teste");
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
		Mockito.when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria));
		
		List<CategoriaDto> categoriasRetornadas = categoriaService.todasAsCategorias();
		
		assertEquals(1, categoriasRetornadas.size());
		assertEquals(categoriaDto, categoriasRetornadas.get(0));
	}
	
	@Test
	public void deve_RetornarCategoriaNaoEncontradaException_QuandoPesquisaPorIdInexistente() {
		Mockito.when(categoriaRepository.findById(2L)).thenReturn(Optional.ofNullable(null));
		assertThrows(CategoriaNaoEncontradaException.class, () -> categoriaService.categoriaPorId(2L));
	}
	
	@Test
	public void deve_RetornarCategoriaNaoEncontradaException_QuandoNaoHaCategoriasCadastradas() {
		Mockito.when(categoriaRepository.findAll()).thenReturn(Arrays.asList());
		assertThrows(CategoriaNaoEncontradaException.class, () -> categoriaService.todasAsCategorias());
	}

}
