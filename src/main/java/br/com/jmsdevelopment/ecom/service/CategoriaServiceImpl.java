package br.com.jmsdevelopment.ecom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.jmsdevelopment.ecom.dto.categoria.CategoriaDto;
import br.com.jmsdevelopment.ecom.helpers.exception.CategoriaNaoEncontradaException;
import br.com.jmsdevelopment.ecom.mappers.CategoriaMapper;
import br.com.jmsdevelopment.ecom.model.Categoria;
import br.com.jmsdevelopment.ecom.repository.CategoriaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	private final CategoriaRepository categoriaRepository;
	private final CategoriaMapper categoriaMapper;

	@Override
	public List<CategoriaDto> todasAsCategorias() {
		List<Categoria> categorias = categoriaRepository.findAll();
		
		if (categorias.isEmpty()) {
			throw new CategoriaNaoEncontradaException("Não existem categorias cadastradas");
		}
		
		return categorias.stream().map(categoriaMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public CategoriaDto categoriaPorId(Long id) {
		return categoriaMapper.toDto(categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNaoEncontradaException()));
	}

}
