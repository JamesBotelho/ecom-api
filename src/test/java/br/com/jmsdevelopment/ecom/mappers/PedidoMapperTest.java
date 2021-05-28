package br.com.jmsdevelopment.ecom.mappers;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.dto.pedido.ItemPedidoDto;
import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;
import br.com.jmsdevelopment.ecom.model.Cliente;
import br.com.jmsdevelopment.ecom.model.ItemPedido;
import br.com.jmsdevelopment.ecom.model.Pedido;
import br.com.jmsdevelopment.ecom.model.Produto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoMapperTest {
    private final PedidoMapper pedidoMapper = Mappers.getMapper(PedidoMapper.class);

    @Test
    public void deve_RetornarPedidoEntitySemId_QuandoTransformaPedidoDtoEmEntity() {
        PedidoDto pedidoDto = new PedidoDto(new BigDecimal(50), null, null);

        Pedido pedidoEntity = pedidoMapper.toPedidoEntity(pedidoDto);

        assertNull(pedidoEntity.getId());
    }

    @Test
    public void deve_RetornarClienteApenasComId_QuandoTransformaPedidoDtoEmEntity() {
        ClienteDto clienteDto = new ClienteDto(1L, "nome", "cpf", "email", "nascimento");
        PedidoDto pedidoDto = new PedidoDto(new BigDecimal(50), null, clienteDto);
        Pedido pedidoEntity = pedidoMapper.toPedidoEntity(pedidoDto);

        Cliente cliente = pedidoEntity.getCliente();

        assertEquals(1L, cliente.getId());
        assertNull(cliente.getNome());
        assertNull(cliente.getCpf());
        assertNull(cliente.getEmail());
        assertNull(cliente.getDataNascimento());
    }

    @Test
    public void deve_RetornarItemPedidoComIdDoProdutoSetado_QuandoTransformaPedidoDtoEmEntity() {
        ItemPedidoDto itemPedidoDtoUm = new ItemPedidoDto(1L, "nomeProdutoUm", 1, new BigDecimal(50));
        ItemPedidoDto itemPedidoDtoDois = new ItemPedidoDto(2L, "nomeProdutoDois", 1, new BigDecimal(100));
        List<ItemPedidoDto> itensPedidoDto = Arrays.asList(itemPedidoDtoUm, itemPedidoDtoDois);
        ClienteDto clienteDto = new ClienteDto(1L, "nome", "cpf", "email", "nascimento");
        PedidoDto pedidoDto = new PedidoDto(new BigDecimal(50), itensPedidoDto, clienteDto);

        Pedido pedidoEntity = pedidoMapper.toPedidoEntity(pedidoDto);

        List<ItemPedido> itensPedidoEntity = pedidoEntity.getItens();

        assertEquals(2, itensPedidoEntity.size());

        Produto itemProdutoUm = itensPedidoEntity.get(0).getProduto();
        Produto itemProdutoDois = itensPedidoEntity.get(1).getProduto();

        assertEquals(1L, itemProdutoUm.getId());
        assertEquals(2L, itemProdutoDois.getId());
        assertNull(itemProdutoUm.getNome());
        assertNull(itemProdutoDois.getNome());
        assertNull(itemProdutoUm.getDescricao());
        assertNull(itemProdutoDois.getDescricao());
        assertNull(itemProdutoUm.getUrlImagem());
        assertNull(itemProdutoDois.getUrlImagem());
        assertNull(itemProdutoUm.getPreco());
        assertNull(itemProdutoDois.getPreco());
        assertNull(itemProdutoUm.getPrecoPromocional());
        assertNull(itemProdutoDois.getPrecoPromocional());
        assertNull(itemProdutoUm.getCategoria());
        assertNull(itemProdutoDois.getCategoria());
    }
}