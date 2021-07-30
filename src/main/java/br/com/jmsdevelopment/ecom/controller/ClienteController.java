package br.com.jmsdevelopment.ecom.controller;

import java.net.URI;
import java.util.List;

import br.com.jmsdevelopment.ecom.dto.carrinho.CarrinhoRetornoDto;
import br.com.jmsdevelopment.ecom.dto.carrinho.ItensCarrinhoDto;
import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;
import br.com.jmsdevelopment.ecom.service.CarrinhoService;
import br.com.jmsdevelopment.ecom.service.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteAlteraSenhaDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteCadastroDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.service.ClienteService;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	private final ClienteService clienteService;
	private final PedidoService pedidoService;
	private final CarrinhoService carrinhoService;
	
	@GetMapping("{id}")
	public ResponseEntity<ClienteDto> recuperaClientePorId(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.recuperarClientePorId(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("{id}/pedidos")
	public Page<PedidoDto> pedidosCliente(@PathVariable Long id,
										  @PageableDefault(sort="dataHora", direction = Sort.Direction.ASC) Pageable pageable) {
		return pedidoService.pedidosDoCliente(id, pageable);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("{id}/carrinho")
	public List<CarrinhoRetornoDto> itensCarrinhoDto(@PathVariable Long id) {
		return carrinhoService.recuperaCarrinho(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("{id}/carrinho")
	public void atualizaCarrinho(@PathVariable Long id, @Valid @RequestBody ItensCarrinhoDto itensCarrinhoDto) {
		carrinhoService.salvaCarrinho(id, itensCarrinhoDto);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("{id}/carrinho")
	public void deletaCarrinho(@PathVariable Long id) {
		carrinhoService.deletaCarrinho(id);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> cadastrarCliente(@Valid @RequestBody ClienteCadastroDto clienteCadastroDto, UriComponentsBuilder uriBuilder) {
		ClienteDto clienteCadastrado = clienteService.cadastrarUsuario(clienteCadastroDto);
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(clienteCadastrado.getId()).toUri();
		
		return ResponseEntity.created(uri).body(clienteCadastrado);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("{id}")
	public void atualizarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
		clienteService.atualizarCliente(id, clienteDto);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("{id}/altera-senha")
	public void alterarSenha(@PathVariable Long id, @RequestBody ClienteAlteraSenhaDto clienteAlteraSenhaDto) {
		clienteService.alterarSenha(id, clienteAlteraSenhaDto);
	}
	
}
