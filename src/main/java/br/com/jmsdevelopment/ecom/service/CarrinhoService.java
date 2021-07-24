package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.carrinho.ItensCarrinhoDto;
import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;

import java.util.List;

public interface CarrinhoService {
    void salvaCarrinho(Long id, ItensCarrinhoDto itensCarrinhoDto);
    List<ProdutoDto> recuperaCarrinho(Long idCliente);
    void deletaCarrinho(Long idCliente);
}
