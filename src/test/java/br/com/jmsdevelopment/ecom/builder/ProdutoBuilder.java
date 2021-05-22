package br.com.jmsdevelopment.ecom.builder;

import java.math.BigDecimal;
import java.util.Arrays;

import br.com.jmsdevelopment.ecom.model.Categoria;
import br.com.jmsdevelopment.ecom.model.Produto;

public class ProdutoBuilder {
	private Long id;
	private String nome;
	private String descricao;
	private String urlImagem;
	private BigDecimal preco;
	private BigDecimal precoPromocional;
	private Long idCategoria;
	private String nomeCategoria;
	
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
	
	public ProdutoBuilder comIdCategoria(Long idDescricao) {
		this.idCategoria = idDescricao;
		return this;
	}
	
	public ProdutoBuilder comNomeCategoria(String nomeDescricao) {
		this.nomeCategoria = nomeDescricao;
		return this;
	}
	
	public Produto build() {
		Categoria categoria = new Categoria(idCategoria, nomeCategoria, Arrays.asList());
		return new Produto(id, nome, descricao, urlImagem, preco, precoPromocional, categoria);
	}
	
}
