package br.com.jmsdevelopment.ecom.dto.carrinho;

import br.com.jmsdevelopment.ecom.dto.produto.ProdutoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarrinhoRetornoDto {
    private ProdutoDto produto;
    private Integer quantidade;
}
