package br.com.jmsdevelopment.ecom.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CLIENTE")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "NOME", length = 255, nullable = false)
	private String nome;
	@Column(name = "CPF", length = 11, nullable = false, unique = true)
	private String cpf;
	@Column(name = "EMAIL", length = 100, nullable = false, unique = true)
	private String email;
	@Column(name = "DATA_NASCIMENTO", nullable = false)
	private LocalDate dataNascimento;
	@Column(name = "SENHA", nullable = false)
	private String senha;
}
