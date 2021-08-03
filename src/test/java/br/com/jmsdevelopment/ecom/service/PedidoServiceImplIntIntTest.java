package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.BaseIntTest;
import br.com.jmsdevelopment.ecom.builder.PedidoDtoBuilder;
import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;
import br.com.jmsdevelopment.ecom.helpers.exception.IdClienteInvalidoException;
import br.com.jmsdevelopment.ecom.helpers.exception.PedidoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoServiceImplIntIntTest extends BaseIntTest {

    @Autowired
    private PedidoService pedidoService;

    private final Pageable pageable = PageRequest.of(0, 10, Sort.by("dataHora").ascending());
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Test
    public void deve_RetornarDoisPedidosEmOrdemCrescenteDeDataHora_QuandoPesquisaPedidoDoCliente() {

        forcaAutenticacao(2L);
        Page<PedidoDto> pedidosPage = pedidoService.pedidosDoCliente(2L, pageable);

        assertEquals(2, pedidosPage.getTotalElements());

        List<PedidoDto> pedidoDtoRetornado = pedidosPage.getContent();

        PedidoDto primeiroPedido = pedidoDtoRetornado.get(0);
        PedidoDto segundoPedido = pedidoDtoRetornado.get(1);

        String horaPrimeiroPedidoEsperada = LocalDateTime.of(2021, 6, 3, 19, 0, 0).format(formatter);
        String horaSegundoPedidoEsperada = LocalDateTime.of(2021, 6, 3, 19, 30, 0).format(formatter);

        assertEquals(horaPrimeiroPedidoEsperada, primeiroPedido.getDataHora());
        assertEquals(horaSegundoPedidoEsperada, segundoPedido.getDataHora());
    }

    @Test
    public void deve_RetornarPedidoNaoEncontradoException_QuandoPesquiasPedidoDeClienteQueNaoPossuiPedido() {
        forcaAutenticacao(3L);
        assertThrows(PedidoNaoEncontradoException.class, () -> pedidoService.pedidosDoCliente(3L, pageable));
    }

    @Test
    public void deve_retornarException_QuandoPesquisaPedidoDeOutroCliente() {
        forcaAutenticacao(3L);
        assertThrows(IdClienteInvalidoException.class, () -> pedidoService.pedidoPorId(1L));
    }

    @Test
    public void deve_retornarException_QuandoInserePedidoEmOutroCliente() {
        PedidoDto pedidoDto = new PedidoDtoBuilder()
                .comCliente(1L, null, null)
                .comItemPedido(1L, null, 1, new BigDecimal(40))
                .comValorTotal(new BigDecimal(40))
                .build();

        forcaAutenticacao(3L);

        assertThrows(IdClienteInvalidoException.class, () -> pedidoService.salvaPedido(pedidoDto));
    }
}