package br.com.jmsdevelopment.ecom.config.security;

import br.com.jmsdevelopment.ecom.builder.ClienteBuilder;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class SecurityTokenServiceImplIntTest {

    private String issuer;
    private String expiracao;
    private String chaveSecreta;
    private Authentication authentication;
    private SecurityTokenService securityTokenService;

    @BeforeEach
    public void beforeEach() {
        this.issuer = "teste-ecom-api";
        this.expiracao = "60";
        this.chaveSecreta = "secret";
        authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return "teste@teste.com";
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return new ClienteBuilder().comId(1L).build();
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "Teste";
            }
        };
        this.securityTokenService = new SecurityTokenServiceImpl(issuer, expiracao, chaveSecreta);
    }

    @Test
    public void deve_retornarToken_quandoChamaGerador() {
        String tokenGerado = this.securityTokenService.gerarToken(authentication);

        assertNotNull(tokenGerado);
    }

    @Test
    public void deve_retornarIdDoCliente_quandoDecodificaToken() {
        String tokenGerado = this.securityTokenService.gerarToken(authentication);

        Long idUsuario = this.securityTokenService.getIdUsuario(tokenGerado);

        assertNotNull(idUsuario);

        assertEquals(1L, idUsuario);
    }

    @Test
    public void deve_retornarTokenExpiredException_quandoDecodificaTokenExpirado() throws InterruptedException {
        this.securityTokenService = new SecurityTokenServiceImpl(issuer, "1", chaveSecreta);
        String tokenGerado = this.securityTokenService.gerarToken(authentication);

        Thread.sleep(1000);

        assertThrows(TokenExpiredException.class, () -> this.securityTokenService.getIdUsuario(tokenGerado));
    }
}