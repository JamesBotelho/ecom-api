package br.com.jmsdevelopment.ecom.builder;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClientePedidoDto;
import br.com.jmsdevelopment.ecom.dto.pedido.ItemPedidoDto;
import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PedidoDtoBuilder {
    private Long id;
    private BigDecimal valorTotal;
    private String dataHora;
    private List<Long> idProduto;
    private List<String> nomeProduto;
    private List<Integer> quantidadeProduto;
    private List<BigDecimal> valorProduto;
    private ClientePedidoDto clienteDto;

    public PedidoDtoBuilder() {
        this.idProduto = new ArrayList<>();
        this.nomeProduto = new ArrayList<>();
        this.quantidadeProduto = new ArrayList<>();
        this.valorProduto = new ArrayList<>();
    }

    public PedidoDtoBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public PedidoDtoBuilder comValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public PedidoDtoBuilder comDataHora(String dataHora) {
        this.dataHora = dataHora;
        return this;
    }

    public PedidoDtoBuilder comItemPedido(Long idProduto, String nomeProduto, Integer quantidadeProduto, BigDecimal valorProduto) {
        this.idProduto.add(idProduto);
        this.nomeProduto.add(nomeProduto);
        this.quantidadeProduto.add(quantidadeProduto);
        this.valorProduto.add(valorProduto);
        return this;
    }

    public PedidoDtoBuilder comCliente(Long id, String nome, String email) {
        this.clienteDto = new ClientePedidoDto(id, nome, email);
        return this;
    }

    private String getNomeProduto(int posicao) {
        if (posicao >= this.nomeProduto.size()) {
            return null;
        }
        return this.nomeProduto.get(posicao);
    }

    private int getQuantidade(int posicao) {
        if (posicao >= this.quantidadeProduto.size()) {
            return 0;
        }
        return this.quantidadeProduto.get(posicao);
    }

    private BigDecimal getValorProduto(int posicao) {
        if (posicao >= this.valorProduto.size()) {
            return null;
        }
        return this.valorProduto.get(posicao);
    }

    public PedidoDto build() {
        List<ItemPedidoDto> itensPedido = new ArrayList<>();
        for (int i = 0; i < idProduto.size(); i++) {
            Long idProduto = this.idProduto.get(i);
            String nomeProduto = getNomeProduto(i);
            Integer quantidadeProduto = getQuantidade(i);
            BigDecimal valorProduto = getValorProduto(i);
            ItemPedidoDto itemPedidoDto = new ItemPedidoDto(idProduto, nomeProduto, quantidadeProduto, valorProduto);
            itensPedido.add(itemPedidoDto);
        }

        return new PedidoDto(this.id, this.valorTotal, itensPedido, this.clienteDto, dataHora);
    }
}
