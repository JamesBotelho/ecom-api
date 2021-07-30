package br.com.jmsdevelopment.ecom.dto.carrinho;

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
    @NotEmpty
    private List<ItemCarrinhoDto> itens;
}
