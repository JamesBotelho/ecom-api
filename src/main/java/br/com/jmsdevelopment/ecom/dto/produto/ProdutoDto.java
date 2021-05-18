package br.com.jmsdevelopment.ecom.dto.produto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProdutoDto {
	private Long id;
	private String nome;
	private String descricao;
	private String urlImagem;
	private BigDecimal preco;
	private BigDecimal precoPromocional;
}
