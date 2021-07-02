package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.builder.PedidoBuilder;
import br.com.jmsdevelopment.ecom.builder.PedidoDtoBuilder;
import br.com.jmsdevelopment.ecom.builder.ProdutoBuilder;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClientePedidoDto;
import br.com.jmsdevelopment.ecom.dto.pedido.ItemPedidoDto;
import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;
import br.com.jmsdevelopment.ecom.helpers.exception.ClienteNaoEncontradoException;
import br.com.jmsdevelopment.ecom.helpers.exception.PaginacaoInvalidaException;
import br.com.jmsdevelopment.ecom.helpers.exception.PedidoInvalidoException;
import br.com.jmsdevelopment.ecom.helpers.exception.PedidoNaoEncontradoException;
import br.com.jmsdevelopment.ecom.mappers.PedidoMapper;
import br.com.jmsdevelopment.ecom.mappers.ProdutoMapper;
import br.com.jmsdevelopment.ecom.model.Pedido;
import br.com.jmsdevelopment.ecom.model.Produto;
import br.com.jmsdevelopment.ecom.repository.PedidoItemRepository;
import br.com.jmsdevelopment.ecom.repository.PedidoRepository;
import br.com.jmsdevelopment.ecom.service.validacao.ValidaNumeroItensPaginacao;
import br.com.jmsdevelopment.ecom.service.validacao.Validacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PedidoServiceImplMockTest {
    @Mock
    private ProdutoService produtoService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoItemRepository pedidoItemRepository;

    @Captor
    private ArgumentCaptor<Pedido> pedidoArgumentCaptor;

    private PedidoMapper pedidoMapper;
    private PedidoService pedidoService;
    private Validacao<Pageable> validaPaginacao;

    private Pedido pedidoBanco;
    private PedidoDto pedidoRetornoEsperadoDto;
    private Produto produtoBancoUm;
    private Produto produtoBancoDois;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.pedidoMapper = Mappers.getMapper(PedidoMapper.class);
        this.validaPaginacao = new ValidaNumeroItensPaginacao();
        this.pedidoService = new PedidoServiceImpl(produtoService, clienteService, pedidoRepository, pedidoMapper, pedidoItemRepository, validaPaginacao);
        pedidoRetornoEsperadoDto = new PedidoDtoBuilder()
                .comId(1L)
                .comValorTotal(new BigDecimal("100"))
                .comItemPedido(1L, "Produto um", 1, new BigDecimal(50))
                .comItemPedido(2L, "Produto dois", 1, new BigDecimal(50))
                .comCliente(1L, "Nome cliente", "email@example.com")
                .comDataHora("2021-05-31T21:00:00")
                .build();
        pedidoBanco = new PedidoBuilder()
                .comId(1L)
                .comValorTotal(new BigDecimal("100"))
                .comItemPedido(1L, "Produto um", 1, new BigDecimal(50))
                .comItemPedido(2L, "Produto dois", 1, new BigDecimal(50))
                .comCliente(1L, "Nome cliente", "cpf-cliente", "email@example.com", "1990-01-01", "123456")
                .comDataHora("2021-05-31T21:00:00")
                .build();

        produtoBancoUm = new ProdutoBuilder()
                .comId(1L)
                .comPreco(new BigDecimal(50))
                .build();
        produtoBancoDois = new ProdutoBuilder()
                .comId(1L)
                .comPreco(new BigDecimal(100))
                .comPrecoPromocional(new BigDecimal(50))
                .build();
    }

    @Test
    public void deve_RetornarPedidoNaoEncontradoException_QuandoPedidoNaoExiste() {
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PedidoNaoEncontradoException.class, () -> pedidoService.pedidoPorId(1L));
    }

    @Test
    public void deve_RetornarPedidoComItens_QuandoPedidoExiste() {
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoBanco));

        PedidoDto pedidoRetornadoDto = pedidoService.pedidoPorId(1L);

        assertEquals(pedidoRetornoEsperadoDto, pedidoRetornadoDto);
    }

    @Test
    public void naoDeve_chamarPersistenciaNoBanco_QuandoClienteEInvalido() {
        PedidoDto pedidoDto = Mockito.mock(PedidoDto.class);
        ClientePedidoDto clienteDto = Mockito.mock(ClientePedidoDto.class);

        Mockito.when(pedidoDto.getCliente()).thenReturn(clienteDto);
        Mockito.when(clienteDto.getId()).thenReturn(1L);
        Mockito.when(clienteService.recuperarClientePorId(1L)).thenThrow(new ClienteNaoEncontradoException());
        assertThrows(ClienteNaoEncontradoException.class, () -> pedidoService.salvaPedido(pedidoDto));
        Mockito.verifyNoInteractions(pedidoRepository);
    }

    @Test
    public void deve_ChamarPersistenciaNoBancoECalcularValorTotalDoPedidoCorretamente_QuandoClienteEPrecoProdutoSaoValidos() {
        Mockito.when(clienteService.recuperarClientePorId(1L)).thenReturn(new ClienteDto());

        ProdutoMapper produtoMapper = Mappers.getMapper(ProdutoMapper.class);

        Mockito.when(produtoService.recuperaProdutoPorId(1L)).thenReturn(produtoMapper.toDto(produtoBancoUm));
        Mockito.when(produtoService.recuperaProdutoPorId(2L)).thenReturn(produtoMapper.toDto(produtoBancoDois));
        Mockito.when(pedidoRepository.saveAndFlush(Mockito.any(Pedido.class))).thenReturn(pedidoBanco);

        pedidoService.salvaPedido(pedidoRetornoEsperadoDto);

        Mockito.verify(pedidoRepository).saveAndFlush(pedidoArgumentCaptor.capture());

        BigDecimal valorTotalCalculado = pedidoArgumentCaptor.getValue().getValorTotal();

        assertEquals(new BigDecimal(100), valorTotalCalculado);

        Mockito.verify(pedidoRepository).saveAndFlush(Mockito.any(Pedido.class));
    }

    @Test
    public void naoDeve_ChamarPersistenciaNoBancoMasDeveRetornarPedidoInvalidoException_QuandoPrecoProdutoEInValidos() {
        Mockito.when(clienteService.recuperarClientePorId(1L)).thenReturn(new ClienteDto());

        ProdutoMapper produtoMapper = Mappers.getMapper(ProdutoMapper.class);

        Mockito.when(produtoService.recuperaProdutoPorId(1L)).thenReturn(produtoMapper.toDto(produtoBancoUm));
        Mockito.when(produtoService.recuperaProdutoPorId(2L)).thenReturn(produtoMapper.toDto(produtoBancoDois));

        ItemPedidoDto itemPedidoDto = pedidoRetornoEsperadoDto.getItens().get(0);
        itemPedidoDto.setValorProduto(new BigDecimal(30));
        pedidoRetornoEsperadoDto.getItens().set(0, itemPedidoDto);

        assertThrows(PedidoInvalidoException.class, () -> pedidoService.salvaPedido(pedidoRetornoEsperadoDto));

        Mockito.verifyNoInteractions(pedidoRepository);
    }

    @Test
    public void deve_lancarPaginacaoInvalidaException_QuandoUltrapassa100ItensDeListagem() {
        assertThrows(PaginacaoInvalidaException.class, () -> pedidoService.pedidosDoCliente(1L, PageRequest.of(0, 101)));
    }
}