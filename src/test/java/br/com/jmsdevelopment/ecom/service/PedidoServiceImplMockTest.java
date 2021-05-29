package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.helpers.exception.PedidoNaoEncontradoException;
import br.com.jmsdevelopment.ecom.mappers.PedidoMapper;
import br.com.jmsdevelopment.ecom.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.pedidoMapper = Mappers.getMapper(PedidoMapper.class);
        this.pedidoService = new PedidoServiceImpl(produtoService, clienteService, pedidoRepository, pedidoMapper);
    }

    @Test
    public void deve_RetornarPedidoNaoEncontradoException_QuandoPedidoNaoExiste() {
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PedidoNaoEncontradoException.class, () -> pedidoService.pedidoPorId(1L));
    }
}