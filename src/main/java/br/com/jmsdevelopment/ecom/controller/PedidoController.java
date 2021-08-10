package br.com.jmsdevelopment.ecom.controller;

import br.com.jmsdevelopment.ecom.dto.erros.ErroDto;
import br.com.jmsdevelopment.ecom.dto.pedido.PedidoDto;
import br.com.jmsdevelopment.ecom.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Retorna um pedido através do seu id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido encontrado"),
            @ApiResponse(code = 400, message = "Requisição inválida", response = ErroDto.class),
            @ApiResponse(code = 403, message = "Não autenticado"),
            @ApiResponse(code = 404, message = "Pedido ou cliente não encontrado", response = ErroDto.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{id}", produces = "application/json")
    public PedidoDto pedidoPorId(@PathVariable Long id) {
        return pedidoService.pedidoPorId(id);
    }

    @ApiOperation(value = "Insere um pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pedido criado", response = ErroDto.class),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 403, message = "Não autenticado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<PedidoDto> inserePedido(@Valid @RequestBody PedidoDto pedidoDto, UriComponentsBuilder uriBuilder) {
        PedidoDto pedidoRetornado = pedidoService.salvaPedido(pedidoDto);

        URI uri = uriBuilder.path("/pedido/{id}").buildAndExpand(pedidoRetornado.getId()).toUri();

        return ResponseEntity.created(uri).body(pedidoRetornado);
    }
}
