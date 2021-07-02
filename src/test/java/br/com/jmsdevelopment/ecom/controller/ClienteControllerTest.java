package br.com.jmsdevelopment.ecom.controller;

import br.com.jmsdevelopment.ecom.builder.ClienteCadastroDtoBuilder;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class ClienteControllerTest extends ControllerTest {
    private URI uri;

    @BeforeEach
    public void beforeEach() throws URISyntaxException {
        uri = new URI("/cliente");
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
}