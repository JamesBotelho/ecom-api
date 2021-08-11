package br.com.jmsdevelopment.ecom.dto.carrinho;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemCarrinhoDto {
    @NotNull(message = "O id do produto é obrigatório")
    @Positive(message = "Id do produto inválido")
    @ApiModelProperty(value = "Id do produto", required = true)
    private Long idProduto;

    @NotNull(message = "A quantidade do produto é obrigatória")
    @Positive(message = "A quantidade do produto é inválida")
    @ApiModelProperty(value = "Quantidade do produto", required = true)
    private Integer quantidade;
}
