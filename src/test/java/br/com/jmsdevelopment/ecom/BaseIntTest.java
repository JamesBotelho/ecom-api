package br.com.jmsdevelopment.ecom;

import br.com.jmsdevelopment.ecom.model.Cliente;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureDataMongo
public abstract class BaseIntTest {
    public void forcaAutenticacao(Long idCliente) {
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
