package br.com.jmsdevelopment.ecom.dto.erros;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ErroDto {
    private String mensagem;
    private List<MensagemDto> erros;
}
