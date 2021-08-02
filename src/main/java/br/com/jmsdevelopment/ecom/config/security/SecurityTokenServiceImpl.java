package br.com.jmsdevelopment.ecom.config.security;

import br.com.jmsdevelopment.ecom.helpers.exception.TokenInvalidoException;
import br.com.jmsdevelopment.ecom.model.Cliente;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SecurityTokenServiceImpl implements SecurityTokenService {

    @Value("${ecom.jwt.issuer}")
    private String issuer;
    @Value("${ecom.jwt.expiracao}")
    private String expiracao;
    @Value("${ecom.jwt.chavesecreta}")
    private String chaveSecreta;

    @Override
    public String gerarToken(Authentication authentication) {
        Cliente clienteLogado = (Cliente) authentication.getPrincipal();

        Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);

        Date atual = new Date();

        Date expiracaoToken = new Date(atual.getTime() + Long.parseLong(expiracao));

        return JWT.create()
                .withIssuer(issuer)
                .withSubject(clienteLogado.getId().toString())
                .withIssuedAt(atual)
                .withExpiresAt(expiracaoToken)
                .sign(algorithm);
    }

    @Override
    public Long getIdUsuario(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            String id = decodedJWT.getSubject();

            return Long.parseLong(id);
        } catch (JWTDecodeException exception) {
            throw new TokenInvalidoException();
        }
    }
}
