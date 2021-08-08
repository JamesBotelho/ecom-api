package br.com.jmsdevelopment.ecom.dto.carrinho;

import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarrinhoRetornoDto {
    @ApiModelProperty(value = "Informações do produto")
    private ProdutoDto produto;
    @ApiModelProperty(value = "Quantidade do produto")
    private Integer quantidade;
}
