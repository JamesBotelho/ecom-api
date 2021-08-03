package br.com.jmsdevelopment.ecom.controller;

import br.com.jmsdevelopment.ecom.builder.PedidoDtoBuilder;
import br.com.jmsdevelopment.ecom.dto.autenticacao.LoginDto;
import br.com.jmsdevelopment.ecom.dto.autenticacao.TokenDto;
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

import static org.junit.jupiter.api.Assertions.*;

class PedidoControllerIntTest extends ControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    private URI uriAuth;
    private URI uri;
    private boolean realizouLogin;

    private String tokenUserUm;
    private String tokenUserDois;
    private String tokenUserTres;

    @BeforeEach
    public void beforeEach() throws Exception {
        uriAuth = new URI("/login");
        uri = new URI("/pedido");
        if (!realizouLogin) {
            LoginDto loginDtoUserUm = new LoginDto("teste@email.com", "1234567890");
            LoginDto loginDtoUserDois = new LoginDto("teste2@email.com", "1234567890");
            LoginDto loginDtoUserTres = new LoginDto("teste3@email.com", "1234567890");
            tokenUserUm = getTokenUsuario(loginDtoUserUm);
            tokenUserDois = getTokenUsuario(loginDtoUserDois);
            tokenUserTres = getTokenUsuario(loginDtoUserTres);
            realizouLogin = true;
        }
    }

    private String getTokenUsuario(LoginDto loginDto) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(uriAuth)
                .content(mapToJson(loginDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        TokenDto tokenDto = mapFromJson(mvcResult.getResponse().getContentAsString(), TokenDto.class);

        return tokenDto.getToken();
    }

    @Test
    public void deve_retornarStatus400_QuandoInserePedidoSemItem() throws Exception {
        PedidoDto pedidoDto = new PedidoDtoBuilder()
                .comCliente(1L, null, null)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .header("Authorization", "Bearer " + tokenUserUm)
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
                .header("Authorization", "Bearer " + tokenUserUm)
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
                .header("Authorization", "Bearer " + tokenUserUm)
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
                .header("Authorization", "Bearer " + tokenUserUm)
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
                .header("Authorization", "Bearer " + tokenUserUm)
                .content(mapToJson(pedidoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }


}