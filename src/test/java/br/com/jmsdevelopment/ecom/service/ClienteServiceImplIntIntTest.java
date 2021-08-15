package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.BaseIntTest;
import br.com.jmsdevelopment.ecom.dto.autenticacao.LoginDto;
import br.com.jmsdevelopment.ecom.dto.autenticacao.TokenDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteAlteraSenhaDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.helpers.exception.ClienteNaoEncontradoException;
import br.com.jmsdevelopment.ecom.helpers.exception.IdClienteInvalidoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceImplIntIntTest extends BaseIntTest {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Test
    public void deve_RetornarClienteExistente_QuandoPesquisaPorIdExistente() {

        forcaAutenticacao(1L);

        ClienteDto clienteDto = clienteService.recuperarClientePorId(1L);

        assertNotNull(clienteDto);
    }

    @Test
    public void deve_retornarException_QuandoPesquisaClienteComIdDeOutroCliente() {
        forcaAutenticacao(2L);

        assertThrows(IdClienteInvalidoException.class, () -> clienteService.recuperarClientePorId(1L));
    }

    @Test
    public void deve_RetornarClienteNaoEncontradoException_QuandoPesquisaPorIdInexistente() {
        forcaAutenticacao(100L);
        assertThrows(ClienteNaoEncontradoException.class, () -> clienteService.recuperarClientePorId(100L));
    }

    @Test
    public void deve_alterarSenhaDoClienteELogarComSucesso_QuandoFazSolicitacaoDeAlteracao() {
        forcaAutenticacao(1L);

        ClienteAlteraSenhaDto alteraSenhaDto = new ClienteAlteraSenhaDto("1234567890", "1234567899");

        clienteService.alterarSenha(1L, alteraSenhaDto);

        TokenDto tokenDto = autenticacaoService.autenticar(new LoginDto("teste@email.com", "1234567899"));

        assertNotNull(tokenDto);
        assertNotNull(tokenDto.getType());
        assertNotNull(tokenDto.getToken());

        alteraSenhaDto = new ClienteAlteraSenhaDto("1234567899", "1234567890");

        clienteService.alterarSenha(1L, alteraSenhaDto);
    }
}