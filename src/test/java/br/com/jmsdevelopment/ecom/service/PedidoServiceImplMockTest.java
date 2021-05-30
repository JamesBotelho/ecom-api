package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.builder.PedidoBuilder;
import br.com.jmsdevelopment.ecom.builder.PedidoDtoBuilder;
import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;
import br.com.jmsdevelopment.ecom.helpers.exception.PedidoNaoEncontradoException;
import br.com.jmsdevelopment.ecom.mappers.PedidoMapper;
import br.com.jmsdevelopment.ecom.model.Pedido;
import br.com.jmsdevelopment.ecom.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PedidoServiceImplMockTest {
    @Mock
    private ProdutoService produtoService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private PedidoRepository pedidoRepository;

    private PedidoMapper pedidoMapper;
    private PedidoService pedidoService;

    private Pedido pedidoBanco;
    private PedidoDto pedidoRetornoEsperadoDto;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.pedidoMapper = Mappers.getMapper(PedidoMapper.class);
        this.pedidoService = new PedidoServiceImpl(produtoService, clienteService, pedidoRepository, pedidoMapper);
        pedidoRetornoEsperadoDto = new PedidoDtoBuilder()
                .comId(1L)
                .comValorTotal(new BigDecimal("100"))
                .comItemPedido(1L, "Produto um", 1, new BigDecimal(50))
                .comItemPedido(2L, "Produto dois", 1, new BigDecimal(50))
                .comCliente(1L, "Nome cliente", null, "email@example.com", null)
                .build();
        pedidoBanco = new PedidoBuilder()
                .comId(1L)
                .comValorTotal(new BigDecimal("100"))
                .comItemPedido(1L, "Produto um", 1, new BigDecimal(50))
                .comItemPedido(2L, "Produto dois", 1, new BigDecimal(50))
                .comCliente(1L, "Nome cliente", "cpf-cliente", "email@example.com", "1990-01-01", "123456")
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
}