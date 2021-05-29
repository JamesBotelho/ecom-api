package br.com.jmsdevelopment.ecom.service;

import java.util.List;

import br.com.jmsdevelopment.ecom.helpers.exception.PedidoInvalidoException;
import br.com.jmsdevelopment.ecom.helpers.exception.PedidoNaoEncontradoException;
import org.springframework.stereotype.Service;

import br.com.jmsdevelopment.ecom.dto.pedido.ItemPedidoDto;
import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;
import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import br.com.jmsdevelopment.ecom.mappers.PedidoMapper;
import br.com.jmsdevelopment.ecom.model.Pedido;
import br.com.jmsdevelopment.ecom.repository.PedidoRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {
	
	private final ProdutoService produtoService;
	private final ClienteService clienteService;
	private final PedidoRepository pedidoRepository;
	private final PedidoMapper pedidoMapper;

	@Override
	public PedidoDto pedidoPorId(Long id) {
		return pedidoMapper.toPedidoDto(pedidoRepository.findById(id).orElseThrow(PedidoNaoEncontradoException::new));
	}

	@Override
	public PedidoDto salvaPedido(PedidoDto pedidoDto) {
		validaCliente(pedidoDto.getCliente().getId());
		validaPrecoProdutos(pedidoDto.getItens());
		
		Pedido pedido = pedidoMapper.toPedidoEntity(pedidoDto);
		Pedido pedidoSalvo = pedidoRepository.save(pedido);
		return pedidoMapper.toPedidoDto(pedidoSalvo);
	}
	
	private void validaCliente(Long id) {
		clienteService.recuperarClientePorId(id);
	}
	
	private void validaPrecoProdutos(List<ItemPedidoDto> produtos) {
		produtos.forEach(produto -> {
			ProdutoDto produtoBanco = produtoService.recuperaProdutoPorId(produto.getIdProduto());
			
			if (produtoBanco.getPrecoPromocional() != null && produto.getValorProduto().compareTo(produtoBanco.getPrecoPromocional()) != 0) {
				throw new PedidoInvalidoException("Preço do produto " + produtoBanco.getNome() +  " inválido");
			} else if (produtoBanco.getPreco().compareTo(produto.getValorProduto()) != 0) {
				throw new PedidoInvalidoException("Preço do produto " + produtoBanco.getNome() +  " inválido");
			}
		});
	}

}
