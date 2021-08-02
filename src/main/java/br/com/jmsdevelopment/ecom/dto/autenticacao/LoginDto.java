package br.com.jmsdevelopment.ecom.dto.autenticacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class LoginDto {

    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }
}
