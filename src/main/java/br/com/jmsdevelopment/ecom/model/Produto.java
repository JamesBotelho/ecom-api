package br.com.jmsdevelopment.ecom.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
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
	private Long id;
	private String nome;
	private String url;
	private BigDecimal preco;
	private BigDecimal precoPromocional;
}
