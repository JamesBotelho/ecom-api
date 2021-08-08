package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.autenticacao.LoginDto;
import br.com.jmsdevelopment.ecom.dto.autenticacao.TokenDto;

public interface AutenticacaoService {
    TokenDto autenticar(LoginDto loginDto);
}
