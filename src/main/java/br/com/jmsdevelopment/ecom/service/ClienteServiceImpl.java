package br.com.jmsdevelopment.ecom.service;

import org.springframework.stereotype.Service;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteAlteraSenhaDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteCadastroDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.mappers.ClienteMapper;
import br.com.jmsdevelopment.ecom.model.Cliente;
import br.com.jmsdevelopment.ecom.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {
	
	private final ClienteRepository clienteRepository;
	private final ClienteMapper clienteMapper;
	
	private Cliente pesquisaPorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	}
	
	@Override
	public ClienteDto recuperarClientePorId(Long id) {
		return clienteMapper.toClienteDto(pesquisaPorId(id));
	}
	
	@Override
	public void cadastrarUsuario(ClienteCadastroDto clienteCadastroDto) {
		Cliente cliente = clienteMapper.fromClienteCadastroToModel(clienteCadastroDto);
		cliente.setId(null);
		clienteRepository.save(cliente);
	}
	
	@Override
	public void atualizarCliente(Long id, ClienteDto clienteDto) {
		Cliente cliente = pesquisaPorId(id);
		cliente = clienteMapper.toModel(clienteDto, cliente);
		cliente.setId(id);
		clienteRepository.save(cliente);
	}
	
	@Override
	public void alterarSenha(Long id, ClienteAlteraSenhaDto clienteAlteraSenhaDto) {
		Cliente cliente = pesquisaPorId(id);
		if (!cliente.getSenha().equals(clienteAlteraSenhaDto.getSenhaAntiga())) {
			throw new RuntimeException("A senha atual é inválida!");
		}
		cliente.setSenha(clienteAlteraSenhaDto.getSenhaNova());
		clienteRepository.save(cliente);
	}
}