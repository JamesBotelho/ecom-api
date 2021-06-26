package br.com.jmsdevelopment.ecom.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PEDIDO")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "VALOR_TOTAL", nullable = false, precision = 2)
	private BigDecimal valorTotal;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;
	@Column(name = "DATA_HORA", nullable = false)
	private LocalDateTime dataHora;
}
