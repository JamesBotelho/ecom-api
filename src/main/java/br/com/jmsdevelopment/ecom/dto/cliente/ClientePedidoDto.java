package br.com.jmsdevelopment.ecom.dto.cliente;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "ID do cliente", required = true)
    private Long id;
    @ApiModelProperty(value = "Nome do cliente")
    private String nome;
    @ApiModelProperty(value = "e-mail do cliente")
    private String email;
}
