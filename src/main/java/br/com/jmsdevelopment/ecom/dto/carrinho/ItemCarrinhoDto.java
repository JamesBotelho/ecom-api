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
    @NotNull
    @Positive
    @ApiModelProperty(value = "Id do produto", required = true)
    private Long idProduto;
    @NotNull
    @Positive
    @ApiModelProperty(value = "Quantidade do produto", required = true)
    private Integer quantidade;
}
