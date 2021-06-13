package br.com.jmsdevelopment.ecom.service;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteAlteraSenhaDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteCadastroDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.helpers.exception.ClienteNaoEncontradoException;
import br.com.jmsdevelopment.ecom.mappers.ClienteMapper;
import br.com.jmsdevelopment.ecom.model.Cliente;
import br.com.jmsdevelopment.ecom.repository.ClienteRepository;
import br.com.jmsdevelopment.ecom.service.validacao.Validacao;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {
	
	private final ClienteRepository clienteRepository;
	private final ClienteMapper clienteMapper;
	@NonNull
	@Qualifier("valida-email")
	private final Validacao<String> validacaoEmail;
	@NonNull
	@Qualifier("valida-cpf")
	private final Validacao<String> validacaoCpf;
	
	private Cliente pesquisaPorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(ClienteNaoEncontradoException::new);
	}
	
	@Override
	public ClienteDto recuperarClientePorId(Long id) {
		return clienteMapper.toClienteDto(pesquisaPorId(id));
	}
	
	@Override
	public ClienteDto cadastrarUsuario(ClienteCadastroDto clienteCadastroDto) {
		Cliente cliente = clienteMapper.fromClienteCadastroToModel(clienteCadastroDto);

		validacaoCpf.validar(cliente.getCpf());
		validacaoEmail.validar(cliente.getEmail());

		cliente.setId(null);
		Cliente clienteSalvo = clienteRepository.save(cliente);
		
		return clienteMapper.toClienteDto(clienteSalvo);
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
