package br.com.jmsdevelopment.ecom.repository;

import br.com.jmsdevelopment.ecom.model.Carrinho;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends MongoRepository<Carrinho, Long> {
}
