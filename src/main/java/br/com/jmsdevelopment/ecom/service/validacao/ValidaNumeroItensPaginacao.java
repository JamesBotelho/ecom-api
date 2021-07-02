package br.com.jmsdevelopment.ecom.service.validacao;

import br.com.jmsdevelopment.ecom.helpers.exception.PaginacaoInvalidaException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
@Qualifier("valida-numero-itens-paginacao")
public class ValidaNumeroItensPaginacao implements Validacao<Pageable> {

    @Override
    public void validar(Pageable validacao) {
        if (validacao.getPageSize() > 100) {
            throw new PaginacaoInvalidaException("O limite de listagem é de 100 itens");
        }
    }
}
