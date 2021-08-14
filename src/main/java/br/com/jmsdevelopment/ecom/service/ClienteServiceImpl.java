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
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@NonNull
	@Qualifier("valida-id-cliente")
	private final Validacao<Long> validaIdCliente;
	
	private Cliente pesquisaPorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(ClienteNaoEncontradoException::new);
	}
	
	@Override
	public ClienteDto recuperarClientePorId(Long id) {
		validaIdCliente.validar(id);
		return clienteMapper.toClienteDto(pesquisaPorId(id));
	}
	
	@Override
	public ClienteDto cadastrarUsuario(ClienteCadastroDto clienteCadastroDto) {
		Cliente cliente = clienteMapper.fromClienteCadastroToModel(clienteCadastroDto);

		validacaoCpf.validar(cliente.getCpf());
		validacaoEmail.validar(cliente.getEmail());

		cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));

		Cliente clienteSalvo = clienteRepository.save(cliente);
		
		return clienteMapper.toClienteDto(clienteSalvo);
	}
	
	@Override
	public void atualizarCliente(Long id, ClienteDto clienteDto) {
		validaIdCliente.validar(id);
		Cliente cliente = pesquisaPorId(id);
		cliente = clienteMapper.toModel(clienteDto, cliente);
		cliente.setId(id);
		clienteRepository.save(cliente);
	}
	
	@Override
	public void alterarSenha(Long id, ClienteAlteraSenhaDto clienteAlteraSenhaDto) {
		validaIdCliente.validar(id);
		Cliente cliente = pesquisaPorId(id);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		if (!bCryptPasswordEncoder.matches(clienteAlteraSenhaDto.getSenhaAntiga(), cliente.getSenha())) {
			throw new RuntimeException("A senha atual é inválida!");
		}
		cliente.setSenha(bCryptPasswordEncoder.encode(clienteAlteraSenhaDto.getSenhaNova()));
		clienteRepository.save(cliente);
	}
}
