package br.com.jmsdevelopment.ecom.mappers;

import br.com.jmsdevelopment.ecom.dto.carrinho.ItemCarrinhoDto;
import br.com.jmsdevelopment.ecom.model.ItemCarrinho;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarrinhoMapper {
    ItemCarrinho toModel(ItemCarrinhoDto itemCarrinhoDto);
}
