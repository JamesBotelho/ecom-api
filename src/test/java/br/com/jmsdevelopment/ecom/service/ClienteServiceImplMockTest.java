package br.com.jmsdevelopment.ecom.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.jmsdevelopment.ecom.builder.ClienteBuilder;
import br.com.jmsdevelopment.ecom.builder.ClienteDtoBuilder;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteAlteraSenhaDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteCadastroDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.mappers.ClienteMapper;
import br.com.jmsdevelopment.ecom.model.Cliente;
import br.com.jmsdevelopment.ecom.repository.ClienteRepository;

class ClienteServiceImplMockTest {
	
	@Mock
	private ClienteRepository clienteRepository;
	
	@Mock
	private ClienteMapper clienteMapper;
	
	@Captor
	public ArgumentCaptor<Cliente> clienteCaptor;
	
	private Cliente cliente;
	private ClienteDto clienteDto;
	private ClienteService clienteService;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		cliente = new ClienteBuilder()
				.comId(1L)
				.comNome("James")
				.comCpf("48313525606")
				.comEmail("james@email.com")
				.comDataNascimento("1990-01-01")
				.comSenha("123456")
				.build();
		
		clienteDto = new ClienteDtoBuilder()
				.comId(1L)
				.comNome("James")
				.comCpf("48313525606")
				.comEmail("james@email.com")
				.comDataNascimento("1990-01-01")
				.build();
		Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.ofNullable(cliente));
		Mockito.when(clienteMapper.toClienteDto(cliente)).thenReturn(clienteDto);
		clienteService = new ClienteServiceImpl(clienteRepository, clienteMapper);
	}
	
	@Test
	public void deve_RetornarUmUsuario_QuandoPesquisaPorId() {
		ClienteDto clienteRetornado = clienteService.recuperarClientePorId(1L);
		
		assertEquals(clienteDto, clienteRetornado);
	}
	
	@Test
	public void deve_CadastrarClienteComIdNulo_QuandoChamaRepository() {
		Mockito.when(clienteMapper.fromClienteCadastroToModel(Mockito.any(ClienteCadastroDto.class))).thenReturn(cliente);
		
		clienteService.cadastrarUsuario(new ClienteCadastroDto());
		
		Mockito.verify(clienteRepository).save(clienteCaptor.capture());
		
		assertNull(clienteCaptor.getValue().getId());
	}
	
	@Test
	public void deve_RetornarRunTimeException_QuandoPesquisaPorIdNaoCadastrado() {
		assertThrows(RuntimeException.class, () -> clienteService.recuperarClientePorId(2L));
	}
	
	@Test
	public void deve_RetornarExceptionAoAlterarSenha_QuandoInformaSenhaAtualInCorreta() {
		ClienteAlteraSenhaDto clienteAlteraSenhaDto = new ClienteAlteraSenhaDto();
		clienteAlteraSenhaDto.setSenhaAntiga("12345678");
		clienteAlteraSenhaDto.setSenhaNova("1234567890");
		assertThrows(RuntimeException.class, () -> clienteService.alterarSenha(1L, clienteAlteraSenhaDto));
	}
}
