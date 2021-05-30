package br.com.jmsdevelopment.ecom.builder;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;
import br.com.jmsdevelopment.ecom.model.Cliente;
import br.com.jmsdevelopment.ecom.model.ItemPedido;
import br.com.jmsdevelopment.ecom.model.Pedido;
import br.com.jmsdevelopment.ecom.model.Produto;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PedidoBuilder {
    private Long id;
    private BigDecimal valorTotal;
    private List<Long> idProduto;
    private List<String> nomeProduto;
    private List<Integer> quantidadeProduto;
    private List<BigDecimal> valorProduto;
    private Cliente cliente;

    public PedidoBuilder() {
        this.idProduto = new ArrayList<>();
        this.nomeProduto = new ArrayList<>();
        this.quantidadeProduto = new ArrayList<>();
        this.valorProduto = new ArrayList<>();
    }

    public PedidoBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public PedidoBuilder comValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public PedidoBuilder comItemPedido(Long idProduto, String nomeProduto, Integer quantidadeProduto, BigDecimal valorProduto) {
        this.idProduto.add(idProduto);
        this.nomeProduto.add(nomeProduto);
        this.quantidadeProduto.add(quantidadeProduto);
        this.valorProduto.add(valorProduto);
        return this;
    }

    public PedidoBuilder comCliente(Long id, String nome, String cpf, String email, String dataNascimento, String senha) {
        this.cliente = new Cliente(id, nome, cpf, email, LocalDate.parse(dataNascimento), senha, null);
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

    public Pedido build() {
        List<ItemPedido> itensPedido = new ArrayList<>();
        for (int i = 0; i < idProduto.size(); i++) {
            Long idProduto = this.idProduto.get(i);
            String nomeProduto = getNomeProduto(i);
            Integer quantidadeProduto = getQuantidade(i);
            BigDecimal valorProduto = getValorProduto(i);
            Produto produto = new Produto(idProduto, nomeProduto, "descrição", "http://url/", valorProduto, null, null);
            ItemPedido itemPedido = new ItemPedido(1L, quantidadeProduto, valorProduto, produto, null);
            itensPedido.add(itemPedido);
        }
        return new Pedido(this.id, this.valorTotal, this.cliente, itensPedido);
    }
}
