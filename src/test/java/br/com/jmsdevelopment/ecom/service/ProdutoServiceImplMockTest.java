package br.com.jmsdevelopment.ecom.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.jmsdevelopment.ecom.builder.ProdutoBuilder;
import br.com.jmsdevelopment.ecom.mappers.ProdutoMapper;
import br.com.jmsdevelopment.ecom.model.Produto;
import br.com.jmsdevelopment.ecom.repository.ProdutoRepository;

class ProdutoServiceImplMockTest {

	@Mock
	private ProdutoRepository produtoRepository;
	
	@Mock
	private ProdutoMapper produtoMapper;
	
	private ProdutoService produtoService;
	
	private Produto produto;
	
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
		produtosEntity = Arrays.asList(produto);
		Mockito.when(produtoRepository.findAll()).thenReturn(produtosEntity);
		
		produtoService = new ProdutoServiceImpl(produtoRepository, produtoMapper);
	}

	@Test
	public void deve_ChamarListagemDoBanco_QuandoListaTodosProdutos() {
		produtoService.todosOsProdutos();
		Mockito.verify(produtoRepository).findAll();
	}
}
