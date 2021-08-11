package br.com.jmsdevelopment.ecom.controller;

import br.com.jmsdevelopment.ecom.dto.erros.ErroDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

	@ApiOperation(value = "Lista todos os produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Produtos encontrados"),
			@ApiResponse(code = 404, message = "Não há produtos cadastrados", response = ErroDto.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = "application/json")
	public Page<ProdutoDto> todosOsProdutos(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return produtoService.todosOsProdutos(pageable);
	}

	@ApiOperation(value = "Lista um produto através do seu id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Produto encontrado"),
			@ApiResponse(code = 404, message = "Produto não encontrado", response = ErroDto.class)
	})
	@GetMapping(path = "{id}", produces = "application/json")
	public ResponseEntity<ProdutoDto> produtoPorId(@PathVariable Long id) {
		return ResponseEntity.ok(produtoService.recuperaProdutoPorId(id));
	}
}
