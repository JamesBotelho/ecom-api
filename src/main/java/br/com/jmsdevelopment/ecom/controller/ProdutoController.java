package br.com.jmsdevelopment.ecom.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import br.com.jmsdevelopment.ecom.service.ProdutoService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	private final ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<ProdutoDto>> todosOsProdutos() {
		return ResponseEntity.ok(produtoService.todosOsProdutos());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProdutoDto> produtoPorId(@PathVariable Long id) {
		return ResponseEntity.ok(produtoService.recuperaProdutoPorId(id));
	}
}
