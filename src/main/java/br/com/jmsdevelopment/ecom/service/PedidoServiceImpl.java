package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.pedido.ItemPedidoDto;
import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;
import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import br.com.jmsdevelopment.ecom.helpers.exception.PedidoInvalidoException;
import br.com.jmsdevelopment.ecom.helpers.exception.PedidoNaoEncontradoException;
import br.com.jmsdevelopment.ecom.mappers.PedidoMapper;
import br.com.jmsdevelopment.ecom.model.ItemPedido;
import br.com.jmsdevelopment.ecom.model.Pedido;
import br.com.jmsdevelopment.ecom.repository.PedidoItemRepository;
import br.com.jmsdevelopment.ecom.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {
	
	private final ProdutoService produtoService;
	private final ClienteService clienteService;
	private final PedidoRepository pedidoRepository;
	private final PedidoMapper pedidoMapper;
	private final PedidoItemRepository pedidoItemRepository;

	@Transactional
	@Override
	public PedidoDto pedidoPorId(Long id) {
		return pedidoMapper.toPedidoDto(pedidoRepository.findById(id).orElseThrow(PedidoNaoEncontradoException::new));
	}

	@Override
	public PedidoDto salvaPedido(PedidoDto pedidoDto) {
		validaCliente(pedidoDto.getCliente().getId());
		validaPrecoProdutos(pedidoDto.getItens());

		Pedido pedido = pedidoMapper.toPedidoEntity(pedidoDto);
		List<ItemPedido> itens = pedido.getItens();
		pedido.setValorTotal(calculaValorTotalPedido(itens));
		pedido.setDataHora(LocalDateTime.now());

		Pedido pedidoSalvo = pedidoRepository.saveAndFlush(pedido);
		Long idPedido = pedidoSalvo.getId();

		itens.forEach(item -> {
			item.setIdPedido(idPedido);
			pedidoItemRepository.saveAndFlush(item);
		});

		return pedidoPorId(idPedido);
	}

	@Transactional
	@Override
	public Page<PedidoDto> pedidosDoCliente(Long idCliente, Pageable pageable) {
		validaCliente(idCliente);
		Page<Pedido> pedidosCliente = pedidoRepository.findByClienteId(idCliente, pageable);

		if (pedidosCliente.isEmpty()) {
			throw new PedidoNaoEncontradoException("Não foi encontrado nenhum pedido para este cliente");
		}

		return pedidosCliente.map(pedidoMapper::toPedidoDto);
	}

	private void validaCliente(Long id) {
		clienteService.recuperarClientePorId(id);
	}
	
	private void validaPrecoProdutos(List<ItemPedidoDto> produtos) {
		produtos.forEach(produto -> {
			ProdutoDto produtoBanco = produtoService.recuperaProdutoPorId(produto.getIdProduto());
			
			if (produtoBanco.getPrecoPromocional() != null && produto.getValorProduto().compareTo(produtoBanco.getPrecoPromocional()) != 0) {
				throw new PedidoInvalidoException("Preço do produto " + produtoBanco.getNome() +  " inválido");
			} else if (produtoBanco.getPrecoPromocional() == null && produtoBanco.getPreco().compareTo(produto.getValorProduto()) != 0) {
				throw new PedidoInvalidoException("Preço do produto " + produtoBanco.getNome() +  " inválido");
			}
		});
	}

	private BigDecimal calculaValorTotalPedido(List<ItemPedido> produtos) {
		return produtos
				.stream()
				.map(ItemPedido::getValorProduto)
				.reduce(BigDecimal::add)
				.orElseThrow(() -> new PedidoInvalidoException("Ocorreu um erro ao calcular o valor total do pedido"));
	}

}
