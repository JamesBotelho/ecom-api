package br.com.jmsdevelopment.ecom.controller;

import br.com.jmsdevelopment.ecom.dto.autenticacao.LoginDto;
import br.com.jmsdevelopment.ecom.dto.autenticacao.TokenDto;
import br.com.jmsdevelopment.ecom.service.AutenticacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public TokenDto autenticar(@Valid @RequestBody LoginDto loginDto) {
        return autenticacaoService.autenticar(loginDto);
    }
}
