package br.com.jmsdevelopment.ecom.dto.autenticacao;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class LoginDto {

    @ApiModelProperty(value = "e-mail do cliente", required = true)
    @Email(message = "O e-mail é inválido")
    @NotBlank(message = "O e-mail não pode ser vazio")
    private String email;

    @ApiModelProperty(value = "senha", required = true)
    @NotBlank(message = "A senha não pode ser vazia")
    private String senha;

    public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }
}
