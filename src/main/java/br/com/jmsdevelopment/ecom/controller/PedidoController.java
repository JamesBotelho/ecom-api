package br.com.jmsdevelopment.ecom.controller;

import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;
import br.com.jmsdevelopment.ecom.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public PedidoDto pedidoPorId(@PathVariable Long id) {
        return pedidoService.pedidoPorId(id);
    }

    @PostMapping
    public ResponseEntity<PedidoDto> inserePedido(@Valid @RequestBody PedidoDto pedidoDto, UriComponentsBuilder uriBuilder) {
        PedidoDto pedidoRetornado = pedidoService.salvaPedido(pedidoDto);

        URI uri = uriBuilder.path("/pedido/{id}").buildAndExpand(pedidoRetornado.getId()).toUri();

        return ResponseEntity.created(uri).body(pedidoRetornado);
    }
}
