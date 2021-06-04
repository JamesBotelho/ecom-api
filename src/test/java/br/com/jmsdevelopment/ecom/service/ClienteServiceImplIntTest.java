package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.helpers.exception.ClienteNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ClienteServiceImplIntTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    public void deve_RetornarClienteExistente_QuandoPesquisaPorIdExistente() {
        ClienteDto clienteDto = clienteService.recuperarClientePorId(1L);

        assertNotNull(clienteDto);
    }

    @Test
    public void deve_RetornarClienteNaoEncontradoException_QuandoPesquisaPorIdInexistente() {
        assertThrows(ClienteNaoEncontradoException.class, () -> clienteService.recuperarClientePorId(3L));
    }
}