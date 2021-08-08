package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.BaseIntTest;
import br.com.jmsdevelopment.ecom.dto.autenticacao.LoginDto;
import br.com.jmsdevelopment.ecom.dto.autenticacao.TokenDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;

import static org.junit.jupiter.api.Assertions.*;

class AutenticacaoServiceImplIntTest extends BaseIntTest {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Test
    public void deve_RetornarTokenGerado_QuandoPassaDadosDeLoginCorretos() {
        TokenDto tokenDto = autenticacaoService.autenticar(new LoginDto("teste@email.com", "1234567890"));

        assertNotNull(tokenDto);

        assertEquals("Bearer", tokenDto.getType());
    }

    @Test
    public void deve_retornarException_QuandoPassaDadosDeLoginIncorretos() {
        assertThrows(AuthenticationException.class, () -> autenticacaoService.autenticar(new LoginDto("teste@email.com", "1234567899")));
    }
}