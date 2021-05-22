package br.com.jmsdevelopment.ecom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jmsdevelopment.ecom.dto.categoria.CategoriaDto;
import br.com.jmsdevelopment.ecom.service.CategoriaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	public final CategoriaService categoriaService;
	
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
}
