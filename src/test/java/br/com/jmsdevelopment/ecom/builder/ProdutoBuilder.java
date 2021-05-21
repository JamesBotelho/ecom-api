package br.com.jmsdevelopment.ecom.builder;

import java.math.BigDecimal;

import br.com.jmsdevelopment.ecom.model.Produto;

public class ProdutoBuilder {
	private Long id;
	private String nome;
	private String descricao;
	private String urlImagem;
	private BigDecimal preco;
	private BigDecimal precoPromocional;
	
	public ProdutoBuilder comId(Long id) {
		this.id = id;
		return this;
	}
	
	public ProdutoBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public ProdutoBuilder comDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
	public ProdutoBuilder comUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
		return this;
	}
	
	public ProdutoBuilder comPreco(BigDecimal preco) {
		this.preco = preco;
		return this;
	}
	
	public ProdutoBuilder comPrecoPromocional(BigDecimal precoPromocional) {
		this.precoPromocional = precoPromocional;
		return this;
	}
	
	public Produto build() {
		return new Produto(id, nome, descricao, urlImagem, preco, precoPromocional);
	}
	
}
