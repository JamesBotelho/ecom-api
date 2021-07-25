package br.com.jmsdevelopment.ecom.controller;

import br.com.jmsdevelopment.ecom.builder.PedidoDtoBuilder;
import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class PedidoControllerIntTest extends ControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    private URI uri;

    @BeforeEach
    public void beforeEach() throws URISyntaxException {
        uri = new URI("/pedido");
    }

    @Test
    public void deve_retornarStatus400_QuandoInserePedidoSemItem() throws Exception {
        PedidoDto pedidoDto = new PedidoDtoBuilder()
                .comCliente(1L, null, null)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(pedidoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deve_retornarStatus400_QuandoInserePedidoSemCliente() throws Exception {
        PedidoDto pedidoDto = new PedidoDtoBuilder()
                .comItemPedido(1L, null, 1, new BigDecimal(40))
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(pedidoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deve_retornarStatus201eIdDiferenteDeZero_QuandoInserePedido() throws Exception {
        PedidoDto pedidoDto = new PedidoDtoBuilder()
                .comItemPedido(1L, null, 1, new BigDecimal(40))
                .comCliente(1L, null, null)
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(pedidoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(201, mvcResult.getResponse().getStatus());

        PedidoDto pedidoRetornado = mapFromJson(mvcResult.getResponse().getContentAsString(), PedidoDto.class);

        assertNotNull(pedidoRetornado.getId());
        assertNotEquals(0L, pedidoRetornado.getId());
    }

    @Test
    public void deve_RetornarStatus400_QuandoValorDoProdutoEhInvalido() throws Exception {
        PedidoDto pedidoDto = new PedidoDtoBuilder()
                .comItemPedido(1L, null, 1, new BigDecimal(30))
                .comCliente(1L, null, null)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(pedidoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deve_RetornarStatus400_QuandoQuantidadeIgualaZero() throws Exception {
        PedidoDto pedidoDto = new PedidoDtoBuilder()
                .comItemPedido(1L, null, 0, new BigDecimal(40))
                .comCliente(1L, null, null)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(pedidoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }


}