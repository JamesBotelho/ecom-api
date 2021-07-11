package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.carrinho.ItensCarrinhoDto;

public interface CarrinhoService {
    void salvaCarrinho(Long id, ItensCarrinhoDto itensCarrinhoDto);
    void recuperaCarrinho(Long idCliente);
    void deletaCarrinho(Long idCliente);
}
