package br.com.jmsdevelopment.ecom.controller;

import br.com.jmsdevelopment.ecom.dto.autenticacao.LoginDto;
import br.com.jmsdevelopment.ecom.dto.autenticacao.TokenDto;
import br.com.jmsdevelopment.ecom.dto.erros.ErroDto;
import br.com.jmsdevelopment.ecom.service.AutenticacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @ApiOperation(value = "Autentica um cliente utilizando login e senha")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário autenticado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Usuários ou senha inválidos", response = ErroDto.class)
    })
    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public TokenDto autenticar(@Valid @RequestBody LoginDto loginDto) {
        return autenticacaoService.autenticar(loginDto);
    }
}
