package br.com.jmsdevelopment.ecom.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PRODUTO")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "NOME", length = 100, nullable = false)
	private String nome;
	@Column(name = "DESCRICAO", length = 500, nullable = false)
	private String descricao;
	@Column(name = "URL_IMAGEM", length = 100, nullable = false)
	private String urlImagem;
	@Column(name = "PRECO", nullable = false)
	private BigDecimal preco;
	@Column(name = "PRECO_PROMOCIONAL", nullable = false)
	private BigDecimal precoPromocional;
}
