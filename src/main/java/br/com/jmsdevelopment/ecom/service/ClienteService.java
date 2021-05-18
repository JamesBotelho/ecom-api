package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteAlteraSenhaDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteCadastroDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;

public interface ClienteService {
	ClienteDto recuperarClientePorId(Long id);
	void cadastrarUsuario(ClienteCadastroDto clienteCadastroDto);
	void atualizarCliente(Long id, ClienteDto clienteDto);
	void alterarSenha(Long id, ClienteAlteraSenhaDto clienteAlteraSenhaDto);
}
