package br.com.jmsdevelopment.ecom.controller;

import br.com.jmsdevelopment.ecom.dto.carrinho.CarrinhoRetornoDto;
import br.com.jmsdevelopment.ecom.dto.carrinho.ItensCarrinhoDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteAlteraSenhaDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteCadastroDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.dto.erros.ErroDto;
import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;
import br.com.jmsdevelopment.ecom.service.CarrinhoService;
import br.com.jmsdevelopment.ecom.service.ClienteService;
import br.com.jmsdevelopment.ecom.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	private final ClienteService clienteService;
	private final PedidoService pedidoService;
	private final CarrinhoService carrinhoService;

	@ApiOperation(value = "Retorna um cliente através do seu id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente encontrado"),
			@ApiResponse(code = 400, message = "Requisição inválida", response = ErroDto.class),
			@ApiResponse(code = 403, message = "Não autenticado"),
			@ApiResponse(code = 404, message = "Cliente não encontrado", response = ErroDto.class)
	})
	@GetMapping(path = "{id}", produces = "application/json")
	public ResponseEntity<ClienteDto> recuperaClientePorId(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.recuperarClientePorId(id));
	}

	@ApiOperation(value = "Retorna os pedidos de um cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Pedido encontrado"),
			@ApiResponse(code = 400, message = "Requisição inválida", response = ErroDto.class),
			@ApiResponse(code = 403, message = "Não autenticado"),
			@ApiResponse(code = 404, message = "Cliente ou pedido(s) não encontrado(s)", response = ErroDto.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "{id}/pedidos", produces = "application/json")
	public Page<PedidoDto> pedidosCliente(@PathVariable Long id,
										  @PageableDefault(sort="dataHora", direction = Sort.Direction.ASC) Pageable pageable) {
		return pedidoService.pedidosDoCliente(id, pageable);
	}

	@ApiOperation(value = "Retorna os produtos que estão no carrinho de um cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Carrinho encontrado"),
			@ApiResponse(code = 400, message = "Requisição inválida", response = ErroDto.class),
			@ApiResponse(code = 403, message = "Não autenticado"),
			@ApiResponse(code = 404, message = "Carrinho não encontrado", response = ErroDto.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "{id}/carrinho", produces = "application/json")
	public List<CarrinhoRetornoDto> itensCarrinhoDto(@PathVariable Long id) {
		return carrinhoService.recuperaCarrinho(id);
	}

	@ApiOperation(value = "Insere produtos no carrinho de um cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Carrinho salvo"),
			@ApiResponse(code = 400, message = "Requisição inválida", response = ErroDto.class),
			@ApiResponse(code = 403, message = "Não autenticado")
	})
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(path = "{id}/carrinho", produces = "application/json")
	public void atualizaCarrinho(@PathVariable Long id, @Valid @RequestBody ItensCarrinhoDto itensCarrinhoDto) {
		carrinhoService.salvaCarrinho(id, itensCarrinhoDto);
	}

	@ApiOperation(value = "Deleta o carrinho de um cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Carrinho deletado"),
			@ApiResponse(code = 400, message = "Requisição inválida", response = ErroDto.class),
			@ApiResponse(code = 403, message = "Não autenticado")
	})
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(path = "{id}/carrinho", produces = "application/json")
	public void deletaCarrinho(@PathVariable Long id) {
		carrinhoService.deletaCarrinho(id);
	}

	@ApiOperation(value = "Realiza o cadastro de um cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente cadastrado"),
			@ApiResponse(code = 400, message = "Requisição inválida", response = ErroDto.class),
			@ApiResponse(code = 409, message = "Cliente já cadastrado", response = ErroDto.class)
	})
	@PostMapping(produces = "application/json")
	public ResponseEntity<ClienteDto> cadastrarCliente(@Valid @RequestBody ClienteCadastroDto clienteCadastroDto, UriComponentsBuilder uriBuilder) {
		ClienteDto clienteCadastrado = clienteService.cadastrarUsuario(clienteCadastroDto);
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(clienteCadastrado.getId()).toUri();
		
		return ResponseEntity.created(uri).body(clienteCadastrado);
	}

	@ApiOperation(value = "Atualiza um cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente atualizado"),
			@ApiResponse(code = 400, message = "Requisição inválida", response = ErroDto.class),
			@ApiResponse(code = 403, message = "Não autenticado"),
			@ApiResponse(code = 404, message = "Cliente não encontrado", response = ErroDto.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(path = "{id}", produces = "application/json")
	public void atualizarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
		clienteService.atualizarCliente(id, clienteDto);
	}

	@ApiOperation(value = "Altera a senha de um cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Senha alterada"),
			@ApiResponse(code = 400, message = "Requisição inválida", response = ErroDto.class),
			@ApiResponse(code = 403, message = "Não autenticado"),
			@ApiResponse(code = 404, message = "Cliente não encontrado", response = ErroDto.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(path = "{id}/altera-senha", produces = "application/json")
	public void alterarSenha(@PathVariable Long id, @RequestBody ClienteAlteraSenhaDto clienteAlteraSenhaDto) {
		clienteService.alterarSenha(id, clienteAlteraSenhaDto);
	}
	
}
