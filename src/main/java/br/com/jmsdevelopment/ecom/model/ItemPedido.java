package br.com.jmsdevelopment.ecom.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ITEM_PEDIDO")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "QUANTIDADE", nullable = false)
	private Integer quantidade;
	@Column(name = "VALOR_PRODUTO", nullable = false, precision = 2)
	private BigDecimal valorProduto;
	@OneToOne
	@JoinColumn(name = "ID_PRODUTO", nullable = false)
	private Produto produto;
	@ManyToOne
	@JoinColumn(name = "ID_PEDIDO", nullable = false)
	private Pedido pedido;
}
