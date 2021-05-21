package br.com.jmsdevelopment.ecom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.jmsdevelopment.ecom.dto.categoria.CategoriaDto;
import br.com.jmsdevelopment.ecom.mappers.CategoriaMapper;
import br.com.jmsdevelopment.ecom.repository.CategoriaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	private final CategoriaRepository categoriaRepository;
	private final CategoriaMapper categoriaMapper;

	@Override
	public List<CategoriaDto> todasAsCategorias() {
		return categoriaRepository.findAll().stream().map(categoriaMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public CategoriaDto categoriaPorId(Long id) {
		return categoriaMapper.toDto(categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada")));
	}

}
