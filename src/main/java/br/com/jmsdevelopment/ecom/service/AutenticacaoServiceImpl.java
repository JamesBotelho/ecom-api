package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.config.security.SecurityTokenService;
import br.com.jmsdevelopment.ecom.dto.autenticacao.LoginDto;
import br.com.jmsdevelopment.ecom.dto.autenticacao.TokenDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutenticacaoServiceImpl implements AutenticacaoService {

    private final AuthenticationManager authenticationManager;
    private final SecurityTokenService tokenService;

    @Override
    public TokenDto autenticar(LoginDto loginDto) {

        UsernamePasswordAuthenticationToken login = loginDto.toUsernamePasswordAuthenticationToken();

        Authentication authentication = authenticationManager.authenticate(login);
        String token = tokenService.gerarToken(authentication);

        return new TokenDto(token, "Bearer");
    }
}
