package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.carrinho.ItemCarrinhoDto;
import br.com.jmsdevelopment.ecom.dto.carrinho.ItensCarrinhoDto;
import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureDataMongo
@ActiveProfiles("test")
class CarrinhoServiceImplIntTest {

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
    public void deve_retornarProdutoComIdCorreto_QuandoRecuperaCarrinho() {
        List<ProdutoDto> produtosDto = carrinhoService.recuperaCarrinho(1L);

        assertEquals(1, produtosDto.size());

        ProdutoDto produtoDto = produtosDto.get(0);

        assertEquals(1L, produtoDto.getId());
    }

    @Test
    public void deve_retornarException_QuandoDeletaCarrinhoEPesquisaPorEle() {
        carrinhoService.deletaCarrinho(1L);

        assertThrows(RuntimeException.class, () -> carrinhoService.recuperaCarrinho(1L));
    }
}