package br.com.jmsdevelopment.ecom.repository;

import br.com.jmsdevelopment.ecom.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoItemRepository extends JpaRepository<ItemPedido, Long> {
}
