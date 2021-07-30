package br.com.jmsdevelopment.ecom.dto.carrinho;

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
    private Long idProduto;
    @NotNull
    @Positive
    private Integer quantidade;
}
