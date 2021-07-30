package br.com.jmsdevelopment.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemCarrinho {
    private Long idProduto;
    private Integer quantidade;
}
