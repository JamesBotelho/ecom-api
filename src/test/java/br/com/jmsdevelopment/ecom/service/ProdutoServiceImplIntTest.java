package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import br.com.jmsdevelopment.ecom.helpers.exception.ProdutoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProdutoServiceImplIntTest {

    @Autowired
    private ProdutoService produtoService;
    
    private Pageable pageable;
    
    @BeforeEach
    public void beforeEach() {
        pageable = PageRequest.of(0, 100, Sort.by("id"));
    }

    @Test
    public void deve_RetornarUmProduto_QuandoCategoriaPossuiUmProdutoCadastrado() {
        Page<ProdutoDto> produtoDtoPage = produtoService.produtosPorCategoria(1L, pageable);

        assertEquals(1, produtoDtoPage.getTotalElements());

        ProdutoDto produtoDto = produtoDtoPage.getContent().get(0);

        assertEquals(1, produtoDto.getId());
        assertEquals("Produto Um", produtoDto.getNome());
    }

    @Test
    public void deve_RetornarProdutoNaoEncontradoException_QuandoNaoHaProdutosCadastradosNaCategoria() {
        assertThrows(ProdutoNaoEncontradoException.class, () -> produtoService.produtosPorCategoria(2L, pageable));
    }
}