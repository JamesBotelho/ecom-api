package br.com.jmsdevelopment.ecom.config.security;

import org.springframework.security.core.Authentication;

public interface SecurityTokenService {
    String gerarToken(Authentication authentication);
    Long getIdUsuario(String token);
}
