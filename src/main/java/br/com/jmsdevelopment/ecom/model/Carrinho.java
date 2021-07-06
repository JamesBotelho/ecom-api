package br.com.jmsdevelopment.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "carrinho")
public class Carrinho {
    @Id
    private Long id;
    private List<ItemCarrinho> itens;
}
