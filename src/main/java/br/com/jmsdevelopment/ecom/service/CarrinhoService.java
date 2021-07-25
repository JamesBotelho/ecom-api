package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.carrinho.CarrinhoRetornoDto;
import br.com.jmsdevelopment.ecom.dto.carrinho.ItensCarrinhoDto;

import java.util.List;

public interface CarrinhoService {
    void salvaCarrinho(Long id, ItensCarrinhoDto itensCarrinhoDto);
    List<CarrinhoRetornoDto> recuperaCarrinho(Long idCliente);
    void deletaCarrinho(Long idCliente);
}
