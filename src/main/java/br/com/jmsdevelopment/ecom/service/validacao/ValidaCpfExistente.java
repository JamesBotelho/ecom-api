package br.com.jmsdevelopment.ecom.service.validacao;

import br.com.jmsdevelopment.ecom.helpers.exception.ClienteInvalidoException;
import br.com.jmsdevelopment.ecom.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Qualifier("valida-cpf")
public class ValidaCpfExistente implements Validacao<String> {

    private final ClienteRepository clienteRepository;

    @Override
    public void validar(String validacao) {
        clienteRepository.findByCpf(validacao).ifPresent((clienteRetornado) -> {
            throw new ClienteInvalidoException("CPF já cadastrado");
        });
    }
}
