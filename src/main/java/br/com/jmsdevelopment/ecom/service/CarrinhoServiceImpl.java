package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.carrinho.CarrinhoRetornoDto;
import br.com.jmsdevelopment.ecom.dto.carrinho.ItensCarrinhoDto;
import br.com.jmsdevelopment.ecom.helpers.exception.CarrinhoNaoEncontradoException;
import br.com.jmsdevelopment.ecom.helpers.exception.ItemCarrinhoInvalidoException;
import br.com.jmsdevelopment.ecom.mappers.CarrinhoMapper;
import br.com.jmsdevelopment.ecom.mappers.ProdutoMapper;
import br.com.jmsdevelopment.ecom.model.Carrinho;
import br.com.jmsdevelopment.ecom.model.ItemCarrinho;
import br.com.jmsdevelopment.ecom.model.Produto;
import br.com.jmsdevelopment.ecom.repository.CarrinhoRepository;
import br.com.jmsdevelopment.ecom.repository.ProdutoRepository;
import br.com.jmsdevelopment.ecom.service.validacao.Validacao;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @NonNull
    @Qualifier("valida-id-cliente")
    private final Validacao<Long> validaIdCliente;

    @Override
    public void salvaCarrinho(Long id, ItensCarrinhoDto itensCarrinhoDto) {

        validaIdCliente.validar(id);

        List<ItemCarrinho> itensCarrinho = itensCarrinhoDto.getItens().stream().map(carrinhoMapper::toModel).collect(Collectors.toList());

        List<Produto> produtos = produtoRepository.findAllById(itensCarrinho.stream().map(ItemCarrinho::getIdProduto).collect(Collectors.toList()));

        if (produtos.size() != itensCarrinho.size()) {
            throw new ItemCarrinhoInvalidoException();
        }

        Carrinho carrinho = new Carrinho(id, itensCarrinho);
        carrinhoRepository.save(carrinho);
    }

    @Override
    public List<CarrinhoRetornoDto> recuperaCarrinho(Long idCliente) {

        validaIdCliente.validar(idCliente);

        Carrinho carrinho = carrinhoRepository.findById(idCliente).orElseThrow(CarrinhoNaoEncontradoException::new);

        List<ItemCarrinho> itensCarrinho = carrinho.getItens();

        List<CarrinhoRetornoDto> itensCarrinhoRetorno = new ArrayList<>();

        itensCarrinho.forEach(item -> {
            Optional<Produto> produtoOptional = produtoRepository.findById(item.getIdProduto());
            produtoOptional.ifPresent(produto -> itensCarrinhoRetorno.add(new CarrinhoRetornoDto(produtoMapper.toDto(produto), item.getQuantidade())));
        });

        return itensCarrinhoRetorno;
    }

    @Override
    public void deletaCarrinho(Long idCliente) {
        validaIdCliente.validar(idCliente);
        carrinhoRepository.deleteById(idCliente);
    }
}
