package br.com.jmsdevelopment.ecom.service.validacao;

import br.com.jmsdevelopment.ecom.helpers.exception.IdClienteInvalidoException;
import br.com.jmsdevelopment.ecom.model.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Qualifier("valida-id-cliente")
public class ValidaIdCliente implements Validacao<Long> {
    @Override
    public void validar(Long validacao) {
        Cliente cliente = (Cliente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!cliente.getId().equals(validacao)) {
            throw new IdClienteInvalidoException();
        }
    }
}
