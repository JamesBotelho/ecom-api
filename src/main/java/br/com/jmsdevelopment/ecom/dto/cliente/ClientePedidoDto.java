package br.com.jmsdevelopment.ecom.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientePedidoDto {
    @NotNull
    @Positive
    private Long id;
    private String nome;
    private String email;
}
