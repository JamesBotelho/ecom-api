package br.com.jmsdevelopment.ecom.dto.carrinho;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemCarrinhoDto {
    private Long idProduto;
    private Integer quantidade;
}
