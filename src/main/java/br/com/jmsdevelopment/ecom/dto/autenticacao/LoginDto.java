package br.com.jmsdevelopment.ecom.dto.autenticacao;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class LoginDto {

    @ApiModelProperty(value = "e-mail do cliente", required = true)
    @NotBlank
    private String email;
    @ApiModelProperty(value = "senha", required = true)
    @NotBlank
    private String senha;

    public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }
}
