package br.com.jmsdevelopment.ecom.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import br.com.jmsdevelopment.ecom.service.ProdutoService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	private final ProdutoService produtoService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<ProdutoDto> todosOsProdutos(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return produtoService.todosOsProdutos(pageable);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProdutoDto> produtoPorId(@PathVariable Long id) {
		return ResponseEntity.ok(produtoService.recuperaProdutoPorId(id));
	}
}
