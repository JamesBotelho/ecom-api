package br.com.jmsdevelopment.ecom.dto.autenticacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String token;
    private String type;
}
