package br.com.jmsdevelopment.ecom.dto.carrinho;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItensCarrinhoDto {
    @Valid
    @NotEmpty(message = "O carrinho deve possuir pelo menos um item")
    @ApiModelProperty(value = "Itens do carrinho a ser salvo", required = true)
    private List<ItemCarrinhoDto> itens;
}
