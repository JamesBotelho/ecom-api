package br.com.jmsdevelopment.ecom.dto.carrinho;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItensCarrinhoDto {
    private List<ItemCarrinhoDto> itens;
}
