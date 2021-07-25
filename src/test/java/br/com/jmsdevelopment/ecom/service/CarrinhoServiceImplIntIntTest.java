package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.BaseIntTest;
import br.com.jmsdevelopment.ecom.dto.carrinho.CarrinhoRetornoDto;
import br.com.jmsdevelopment.ecom.dto.carrinho.ItemCarrinhoDto;
import br.com.jmsdevelopment.ecom.dto.carrinho.ItensCarrinhoDto;
import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoServiceImplIntIntTest extends BaseIntTest {

    @Autowired
    private CarrinhoService carrinhoService;

    @BeforeEach
    public void beforeEach() {
        ItensCarrinhoDto itensCarrinhoDto = new ItensCarrinhoDto(Collections.singletonList(new ItemCarrinhoDto(1L, 1)));
        carrinhoService.salvaCarrinho(1L, itensCarrinhoDto);
    }

    @AfterEach
    public void afterEach() {
        carrinhoService.deletaCarrinho(1L);
    }

    @Test
    public void deve_retornarProdutoComIdEQuantidadeCorretos_QuandoRecuperaCarrinho() {
        List<CarrinhoRetornoDto> itensRetornados = carrinhoService.recuperaCarrinho(1L);

        assertEquals(1, itensRetornados.size());

        CarrinhoRetornoDto carrinhoRetornoDto = itensRetornados.get(0);
        ProdutoDto produtoRetornadoDto = carrinhoRetornoDto.getProduto();

        assertEquals(1L, produtoRetornadoDto.getId());
        assertEquals(1, carrinhoRetornoDto.getQuantidade());
    }

    @Test
    public void deve_retornarException_QuandoDeletaCarrinhoEPesquisaPorEle() {
        carrinhoService.deletaCarrinho(1L);

        assertThrows(RuntimeException.class, () -> carrinhoService.recuperaCarrinho(1L));
    }
}