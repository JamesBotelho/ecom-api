package br.com.jmsdevelopment.ecom.controller;

import java.util.List;

import br.com.jmsdevelopment.ecom.dto.MensagemDto;
import br.com.jmsdevelopment.ecom.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jmsdevelopment.ecom.dto.categoria.CategoriaDto;
import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import br.com.jmsdevelopment.ecom.service.CategoriaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	private final CategoriaService categoriaService;
	private final ProdutoService produtoService;

	@ApiOperation(value = "Lista todas as categorias de produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Categoria encontrada"),
			@ApiResponse(code = 404, message = "Não há categorias cadastradas", response = MensagemDto.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = "application/json")
	public List<CategoriaDto> todasAsCategorias() {
		return categoriaService.todasAsCategorias();
	}

	@ApiOperation(value = "Retorna uma categoria de produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Categoria encontrada"),
			@ApiResponse(code = 404, message = "Categoria não encontrada", response = MensagemDto.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "{id}", produces = "application/json")
	public CategoriaDto categoriaPorId(@PathVariable Long id) {
		return categoriaService.categoriaPorId(id);
	}

	@ApiOperation(value = "Lista os produtos de uma categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Produtos encontrados"),
			@ApiResponse(code = 404, message = "Não há produtos cadastrados na categoria selecionada", response = MensagemDto.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "{id}/produtos", produces = "application/json")
	public Page<ProdutoDto> produtosDaCategoria(@PathVariable Long id,
												@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return produtoService.produtosPorCategoria(id, pageable);
	}
}
