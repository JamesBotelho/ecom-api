package br.com.jmsdevelopment.ecom.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import br.com.jmsdevelopment.ecom.service.validacao.Validacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
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
import br.com.jmsdevelopment.ecom.helpers.exception.ClienteNaoEncontradoException;
import br.com.jmsdevelopment.ecom.mappers.ClienteMapper;
import br.com.jmsdevelopment.ecom.model.Cliente;
import br.com.jmsdevelopment.ecom.repository.ClienteRepository;

class ClienteServiceImplMockTest {
	
	@Mock
	private ClienteRepository clienteRepository;

	private ClienteMapper clienteMapper;

	@Mock
	private Validacao<String> validacao;

	@Mock
	private Validacao<Long> validacaoLong;
	
	@Captor
	public ArgumentCaptor<Cliente> clienteCaptor;
	
	private Cliente cliente;
	private ClienteDto clienteDto;
	private ClienteService clienteService;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		clienteMapper = Mappers.getMapper(ClienteMapper.class);
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
		clienteService = new ClienteServiceImpl(clienteRepository, clienteMapper, validacao, validacao, validacaoLong);
	}
	
	@Test
	public void deve_RetornarUmUsuarioEChamarValidacaoDeIdDeCliente_QuandoPesquisaPorId() {
		ClienteDto clienteRetornado = clienteService.recuperarClientePorId(1L);

		Mockito.verify(validacaoLong).validar(1L);
		
		assertEquals(clienteDto, clienteRetornado);
	}
	
	@Test
	public void deve_CadastrarClienteComIdNulo_QuandoChamaRepository() {

		ClienteCadastroDto clienteCadastroDto = new ClienteCadastroDto();
		clienteCadastroDto.setSenha("senhaqualquer");

		clienteService.cadastrarUsuario(clienteCadastroDto);
		
		Mockito.verify(clienteRepository).save(clienteCaptor.capture());
		
		assertNull(clienteCaptor.getValue().getId());
	}
	
	@Test
	public void deve_RetornarClienteNaoEncontradoException_QuandoPesquisaPorIdNaoCadastrado() {
		assertThrows(ClienteNaoEncontradoException.class, () -> clienteService.recuperarClientePorId(2L));
	}
	
	@Test
	public void deve_RetornarExceptionAoAlterarSenha_QuandoInformaSenhaAtualInCorreta() {
		ClienteAlteraSenhaDto clienteAlteraSenhaDto = new ClienteAlteraSenhaDto();
		clienteAlteraSenhaDto.setSenhaAntiga("12345678");
		clienteAlteraSenhaDto.setSenhaNova("1234567890");
		assertThrows(RuntimeException.class, () -> clienteService.alterarSenha(1L, clienteAlteraSenhaDto));
	}
}
