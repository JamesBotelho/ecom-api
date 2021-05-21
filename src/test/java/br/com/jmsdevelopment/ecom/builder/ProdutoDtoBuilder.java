package br.com.jmsdevelopment.ecom.builder;

import java.math.BigDecimal;

import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;

public class ProdutoDtoBuilder {
	private Long id;
	private String nome;
	private String descricao;
	private String urlImagem;
	private BigDecimal preco;
	private BigDecimal precoPromocional;
	
	public ProdutoDtoBuilder() {}

	public ProdutoDtoBuilder comId(Long id) {
		this.id = id;
		return this;
	}

	public ProdutoDtoBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public ProdutoDtoBuilder comDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public ProdutoDtoBuilder comUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
		return this;
	}

	public ProdutoDtoBuilder comPreco(BigDecimal preco) {
		this.preco = preco;
		return this;
	}

	public ProdutoDtoBuilder comPrecoPromocional(BigDecimal precoPromocional) {
		this.precoPromocional = precoPromocional;
		return this;
	}
	
	public ProdutoDto build() {
		return new ProdutoDto(id, nome, descricao, urlImagem, preco, precoPromocional);
	}
}
