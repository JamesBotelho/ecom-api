package br.com.jmsdevelopment.ecom.controller;

import br.com.jmsdevelopment.ecom.builder.ClienteCadastroDtoBuilder;
import br.com.jmsdevelopment.ecom.builder.ClienteDtoBuilder;
import br.com.jmsdevelopment.ecom.dto.autenticacao.LoginDto;
import br.com.jmsdevelopment.ecom.dto.autenticacao.TokenDto;
import br.com.jmsdevelopment.ecom.dto.carrinho.CarrinhoRetornoDto;
import br.com.jmsdevelopment.ecom.dto.carrinho.ItemCarrinhoDto;
import br.com.jmsdevelopment.ecom.dto.carrinho.ItensCarrinhoDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteControllerIntTest extends ControllerIntTest {
    private URI uriAuth;
    private URI uri;

    private String tokenUserUm;
    private String tokenUserDois;
    private String tokenUserTres;

    private boolean realizouLogin = false;

    @BeforeEach
    public void beforeEach() throws Exception {
        uriAuth = new URI("/login");
        uri = new URI("/cliente");

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
    public void deve_retornarStatus201eIdDiferenteDeZero_QuandoInsereCliente() throws Exception {
        ClienteDto clienteDto = new ClienteCadastroDtoBuilder()
                .comCpf("94587191000")
                .comDataNascimento("1990-01-01")
                .comEmail("exemplo@email.com")
                .comNome("Teste")
                .comSenha("1234567890")
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(clienteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(201, mvcResult.getResponse().getStatus());
        ClienteDto clienteRetornado = mapFromJson(mvcResult.getResponse().getContentAsString(), ClienteDto.class);

        assertNotNull(clienteRetornado.getId());
        assertNotEquals(0L, clienteRetornado.getId());
    }

    @Test
    public void deve_RetornarStatus400_QuandoNomeNulo() throws Exception {
        ClienteDto clienteDto = new ClienteCadastroDtoBuilder()
                .comCpf("94587191000")
                .comDataNascimento("1990-01-01")
                .comEmail("exemplo@email.com")
                .comNome(null)
                .comSenha("1234567890")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(clienteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deve_RetornarStatus400_QuandoEmailNull() throws Exception {
        ClienteDto clienteDto = new ClienteCadastroDtoBuilder()
                .comCpf("94587191000")
                .comDataNascimento("1990-01-01")
                .comEmail(null)
                .comNome("Teste")
                .comSenha("1234567890")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(clienteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deve_RetornarStatus400_QuandoEmailInvalido() throws Exception {
        ClienteDto clienteDto = new ClienteCadastroDtoBuilder()
                .comCpf("94587191000")
                .comDataNascimento("1990-01-01")
                .comEmail("teste")
                .comNome("Teste")
                .comSenha("1234567890")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(clienteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deve_RetornarStatus409_QuandoEmailExiste() throws Exception {
        ClienteDto clienteDto = new ClienteCadastroDtoBuilder()
                .comCpf("94587191000")
                .comDataNascimento("1990-01-01")
                .comEmail("teste@email.com")
                .comNome("Teste")
                .comSenha("1234567890")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(clienteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(409));
    }

    @Test
    public void deve_RetornarStatus400_QuandoCpfInvalido() throws Exception {
        ClienteDto clienteDto = new ClienteCadastroDtoBuilder()
                .comCpf("94587191001")
                .comDataNascimento("1990-01-01")
                .comEmail("teste")
                .comNome("email@email.com")
                .comSenha("1234567890")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(clienteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deve_RetornarStatus400_QuandoCpfNull() throws Exception {
        ClienteDto clienteDto = new ClienteCadastroDtoBuilder()
                .comCpf(null)
                .comDataNascimento("1990-01-01")
                .comEmail("teste")
                .comNome("email@email.com")
                .comSenha("1234567890")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(clienteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deve_RetornarStatus409_QuandoCpfExiste() throws Exception {
        ClienteDto clienteDto = new ClienteCadastroDtoBuilder()
                .comCpf("48313525606")
                .comDataNascimento("1990-01-01")
                .comEmail("teste@email.com")
                .comNome("Teste")
                .comSenha("1234567890")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(clienteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(409));
    }

    @Test
    public void deve_RetornarStatus400_QuandoSenhaInvalida() throws Exception {
        ClienteCadastroDtoBuilder clienteCadastroDtoBuilder = new ClienteCadastroDtoBuilder()
                .comCpf("48313525606")
                .comDataNascimento("1990-01-01")
                .comEmail("teste@email.com")
                .comNome("Teste")
                .comSenha("1234567");
        ClienteDto clienteDto = clienteCadastroDtoBuilder
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(clienteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));

        clienteCadastroDtoBuilder.comSenha("12345678901234567890123");
        clienteDto = clienteCadastroDtoBuilder.build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(clienteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deve_RetornarStatus400_QuandoSenhaNull() throws Exception {
        ClienteDto clienteDto = new ClienteCadastroDtoBuilder()
                .comCpf("48313525606")
                .comDataNascimento("1990-01-01")
                .comEmail("teste@email.com")
                .comNome("Teste")
                .comSenha(null)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(mapToJson(clienteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deve_AlterarNascimentoeNome_QuandoAtualizaCliente() throws Exception {
        ClienteDto clienteDto = new ClienteDtoBuilder()
                .comCpf("94587191000")
                .comDataNascimento("1990-01-20")
                .comEmail("exemplo@email.com")
                .comNome("Zé Maria")
                .comId(2L)
                .build();

        uri = new URI("/cliente/1");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .content(mapToJson(clienteDto))
                .header("Authorization", "Bearer " + tokenUserUm)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        MvcResult mvcResultPesquisaCliente = mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .header("Authorization", "Bearer " + tokenUserUm)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(200, mvcResultPesquisaCliente.getResponse().getStatus());

        ClienteDto clienteRetornadoDto = mapFromJson(mvcResultPesquisaCliente.getResponse().getContentAsString(StandardCharsets.UTF_8), ClienteDto.class);

        assertEquals(1L, clienteRetornadoDto.getId());
        assertEquals("Zé Maria", clienteRetornadoDto.getNome());
        assertEquals("1990-01-20", clienteRetornadoDto.getDataNascimento());
        assertNotEquals("exemplo@email.com", clienteRetornadoDto.getEmail());
        assertNotEquals("94587191000", clienteRetornadoDto.getCpf());
    }

    @Test
    public void deve_RetornarStatus400_QuandoTentaSalvarCarrinhoSemItens() throws Exception {
        uri = new URI("/cliente/1/carrinho");

        mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .header("Authorization", "Bearer " + tokenUserUm)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deve_RetornarStatus400_QuandoTentaSalvarCarrinhoComItensInvalidos() throws Exception {
        uri = new URI("/cliente/1/carrinho");

        ItensCarrinhoDto itensCarrinhoDto = new ItensCarrinhoDto(Collections.singletonList(new ItemCarrinhoDto(100L, 1)));

        mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .header("Authorization", "Bearer " + tokenUserUm)
                .content(mapToJson(itensCarrinhoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deve_RetornarStatus200_QuandoSalvaCarrinho() throws Exception {
        uri = new URI("/cliente/2/carrinho");

        ItensCarrinhoDto itensCarrinhoDto = new ItensCarrinhoDto(Collections.singletonList(new ItemCarrinhoDto(1L, 1)));

        mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .header("Authorization", "Bearer " + tokenUserDois)
                .content(mapToJson(itensCarrinhoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void deve_RetornarStatus404_QuandoTentaRecuperarCarrinhoInexistente() throws Exception {
        uri = new URI("/cliente/3/carrinho");

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .header("Authorization", "Bearer " + tokenUserTres)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    public void deve_RetornarStatus400_QuandoTentaRecuperarCarrinhoDeOutroCliente() throws Exception {
        uri = new URI("/cliente/3/carrinho");

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .header("Authorization", "Bearer " + tokenUserDois)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deve_retornarCarrinhoSalvo_QuandoRealizaRequisicao() throws Exception {
        uri = new URI("/cliente/1/carrinho");

        ItensCarrinhoDto itensCarrinhoDto = new ItensCarrinhoDto(Collections.singletonList(new ItemCarrinhoDto(1L, 1)));

        mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .header("Authorization", "Bearer " + tokenUserUm)
                .content(mapToJson(itensCarrinhoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

        MvcResult mvcResultCarrinho = mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .header("Authorization", "Bearer " + tokenUserUm)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(200, mvcResultCarrinho.getResponse().getStatus());

        List<CarrinhoRetornoDto> itensRetornados = mapFromJsonList(mvcResultCarrinho.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
        });

        assertEquals(1, itensRetornados.size());

        CarrinhoRetornoDto carrinhoRetornoDto = itensRetornados.get(0);

        assertEquals(1, carrinhoRetornoDto.getQuantidade());

        ProdutoDto produtoRetornadoDto = carrinhoRetornoDto.getProduto();

        assertEquals(1L, produtoRetornadoDto.getId());
    }

    @Test
    public void deve_retornarStatus404_quandoRecuperarCarrinhoQueFoiDeletado() throws Exception {
        uri = new URI("/cliente/1/carrinho");

        ItensCarrinhoDto itensCarrinhoDto = new ItensCarrinhoDto(Collections.singletonList(new ItemCarrinhoDto(1L, 1)));

        mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .header("Authorization", "Bearer " + tokenUserUm)
                .content(mapToJson(itensCarrinhoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri)
                .header("Authorization", "Bearer " + tokenUserUm))
                .andExpect(MockMvcResultMatchers.status().is(200));

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .header("Authorization", "Bearer " + tokenUserUm)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}