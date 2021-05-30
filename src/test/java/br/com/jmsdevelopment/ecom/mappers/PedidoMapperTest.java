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
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PedidoMapperTest {
    private final PedidoMapper pedidoMapper = Mappers.getMapper(PedidoMapper.class);

    @Test
    public void deve_RetornarPedidoEntitySemId_QuandoTransformaPedidoDtoEmEntity() {
        PedidoDto pedidoDto = new PedidoDto(1L, new BigDecimal(50), null, null);

        Pedido pedidoEntity = pedidoMapper.toPedidoEntity(pedidoDto);

        assertNull(pedidoEntity.getId());
    }

    @Test
    public void deve_RetornarClienteApenasComId_QuandoTransformaPedidoDtoEmEntity() {
        ClienteDto clienteDto = new ClienteDto(1L, "nome", "cpf", "email", "nascimento");
        PedidoDto pedidoDto = new PedidoDto(1L, new BigDecimal(50), null, clienteDto);
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
        PedidoDto pedidoDto = new PedidoDto(1L, new BigDecimal(50), itensPedidoDto, clienteDto);

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

    @Test
    public void deve_RetornarPedidoDto_QuandoConvertePedidoEntityParaPedidoDto() {
        BigDecimal valorTotal = new BigDecimal(50);
        Cliente cliente = new Cliente(1L, "Teste", "48313525606", "teste@email.com", LocalDate.of(1995, Month.JULY, 26), "1234567890", null);
        Produto produto = new Produto(1L, "Nome Produto", "Descrição Produto", "http://url_imagem", new BigDecimal(50), null, null);
        ItemPedido itemPedidoUm = new ItemPedido(1L, 2, new BigDecimal(50), produto, null);
        Pedido pedido = new Pedido(1L, valorTotal, cliente, Arrays.asList(itemPedidoUm));

        PedidoDto pedidoConvertidoDto = pedidoMapper.toPedidoDto(pedido);
        List<ItemPedidoDto> itensConvertidoDto = pedidoConvertidoDto.getItens();

        assertEquals(1L, pedidoConvertidoDto.getId());
        assertEquals(new BigDecimal(50), pedidoConvertidoDto.getValorTotal());
        assertEquals(1, itensConvertidoDto.size());

        ItemPedidoDto itemPedidoConvertidoDto = itensConvertidoDto.get(0);

        assertEquals(1L, itemPedidoConvertidoDto.getIdProduto());
        assertEquals("Nome Produto", itemPedidoConvertidoDto.getNomeProduto());
        assertEquals(2, itemPedidoConvertidoDto.getQuantidade());
        assertEquals(new BigDecimal(50), itemPedidoConvertidoDto.getValorProduto());

        ClienteDto clienteConvertidoDto = pedidoConvertidoDto.getCliente();

        assertEquals(1L, clienteConvertidoDto.getId());
        assertEquals("Teste", clienteConvertidoDto.getNome());
        assertEquals("teste@email.com", clienteConvertidoDto.getEmail());
        assertNull(clienteConvertidoDto.getCpf());
        assertNull(clienteConvertidoDto.getDataNascimento());
    }
}