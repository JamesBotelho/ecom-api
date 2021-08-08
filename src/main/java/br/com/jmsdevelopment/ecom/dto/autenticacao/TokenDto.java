package br.com.jmsdevelopment.ecom.dto.autenticacao;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    @ApiModelProperty(value = "Token gerado ao realizar o login")
    private String token;
    @ApiModelProperty(value = "Tipo do token")
    private String type;
}
