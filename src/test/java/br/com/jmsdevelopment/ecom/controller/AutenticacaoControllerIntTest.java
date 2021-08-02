package br.com.jmsdevelopment.ecom.controller;

import br.com.jmsdevelopment.ecom.dto.autenticacao.LoginDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class AutenticacaoControllerIntTest extends ControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    private URI uri;

    @BeforeEach
    public void beforeEach() throws URISyntaxException {
        uri = new URI("/login");
    }

    @Test
    public void deve_retornarStatus200_QuandoAutenticaCorretamente() throws Exception {
        LoginDto loginDto = new LoginDto("teste@email.com", "1234567890");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(mapToJson(loginDto))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void deve_retornarStatus401_QuandoAutenticaIncorretamente() throws Exception {
        LoginDto loginDto = new LoginDto("teste@email.com", "1234567899");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(mapToJson(loginDto))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(401));
    }
}