
package br.com.jmsdevelopment.ecom.config.security;

import br.com.jmsdevelopment.ecom.model.Cliente;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final SecurityTokenService securityTokenService;

    private String getToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        if (token == null || token.trim().isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.replace("Bearer ", "");
    }

    private void autenticarCliente(String token) {
        try {
            Long idUsuario = securityTokenService.getIdUsuario(token);

            Cliente cliente = new Cliente();
            cliente.setId(idUsuario);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(cliente, token, cliente.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(httpServletRequest);

        if (token != null) {
            autenticarCliente(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
