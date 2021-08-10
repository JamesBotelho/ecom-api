package br.com.jmsdevelopment.ecom.controller;

import br.com.jmsdevelopment.ecom.dto.categoria.CategoriaDto;
import br.com.jmsdevelopment.ecom.dto.erros.ErroDto;
import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import br.com.jmsdevelopment.ecom.service.CategoriaService;
import br.com.jmsdevelopment.ecom.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	private final CategoriaService categoriaService;
	private final ProdutoService produtoService;

	@ApiOperation(value = "Lista todas as categorias de produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Categoria encontrada"),
			@ApiResponse(code = 404, message = "Não há categorias cadastradas", response = ErroDto.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = "application/json")
	public List<CategoriaDto> todasAsCategorias() {
		return categoriaService.todasAsCategorias();
	}

	@ApiOperation(value = "Retorna uma categoria de produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Categoria encontrada"),
			@ApiResponse(code = 404, message = "Categoria não encontrada", response = ErroDto.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "{id}", produces = "application/json")
	public CategoriaDto categoriaPorId(@PathVariable Long id) {
		return categoriaService.categoriaPorId(id);
	}

	@ApiOperation(value = "Lista os produtos de uma categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Produtos encontrados"),
			@ApiResponse(code = 404, message = "Não há produtos cadastrados na categoria selecionada", response = ErroDto.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "{id}/produtos", produces = "application/json")
	public Page<ProdutoDto> produtosDaCategoria(@PathVariable Long id,
												@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return produtoService.produtosPorCategoria(id, pageable);
	}
}
