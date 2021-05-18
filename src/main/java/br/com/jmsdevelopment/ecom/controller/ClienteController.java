package br.com.jmsdevelopment.ecom.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	private final ClienteService clienteService;
	
	@GetMapping("{id}")
	public ResponseEntity<ClienteDto> recuperaClientePorId(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.recuperarClientePorId(id));
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody ClienteCadastroDto clienteCadastroDto, UriComponentsBuilder uriBuilder) {
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
