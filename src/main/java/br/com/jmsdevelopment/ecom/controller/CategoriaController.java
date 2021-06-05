package br.com.jmsdevelopment.ecom.controller;

import java.util.List;

import br.com.jmsdevelopment.ecom.service.ProdutoService;
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
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<CategoriaDto> todasAsCategorias() {
		return categoriaService.todasAsCategorias();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("{id}")
	public CategoriaDto categoriaPorId(@PathVariable Long id) {
		return categoriaService.categoriaPorId(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("{id}/produtos")
	public Page<ProdutoDto> produtosDaCategoria(@PathVariable Long id,
												@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return produtoService.produtosPorCategoria(id, pageable);
	}
}
