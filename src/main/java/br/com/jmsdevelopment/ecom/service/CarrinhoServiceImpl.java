package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.carrinho.ItensCarrinhoDto;
import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import br.com.jmsdevelopment.ecom.mappers.CarrinhoMapper;
import br.com.jmsdevelopment.ecom.mappers.ProdutoMapper;
import br.com.jmsdevelopment.ecom.model.Carrinho;
import br.com.jmsdevelopment.ecom.model.ItemCarrinho;
import br.com.jmsdevelopment.ecom.model.Produto;
import br.com.jmsdevelopment.ecom.repository.CarrinhoRepository;
import br.com.jmsdevelopment.ecom.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarrinhoServiceImpl implements CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final CarrinhoMapper carrinhoMapper;
    private final ProdutoMapper produtoMapper;

    @Override
    public void salvaCarrinho(Long id, ItensCarrinhoDto itensCarrinhoDto) {
        List<ItemCarrinho> itensCarrinho = itensCarrinhoDto.getItens().stream().map(carrinhoMapper::toModel).collect(Collectors.toList());

        Carrinho carrinho = new Carrinho(id, itensCarrinho);
        carrinhoRepository.save(carrinho);
    }

    @Override
    public List<ProdutoDto> recuperaCarrinho(Long idCliente) {
        Carrinho carrinho = carrinhoRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Não há carrinho para este cliente"));

        List<ItemCarrinho> itensCarrinho = carrinho.getItens();

        List<Produto> produtos = new ArrayList<>();

        itensCarrinho.forEach(item -> {
            Optional<Produto> produto = produtoRepository.findById(item.getIdProduto());
            produto.ifPresent(produtos::add);
        });

        return produtos.stream().map(produtoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deletaCarrinho(Long idCliente) {
        carrinhoRepository.deleteById(idCliente);
    }
}
