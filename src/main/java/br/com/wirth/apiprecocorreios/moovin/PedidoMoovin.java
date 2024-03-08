package br.com.wirth.apiprecocorreios.moovin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class PedidoMoovin implements Serializable {
    @JsonProperty("codigo_pedido")
    private String codigo_pedido;
    @JsonProperty("dados_transporte")
    DadosTransporteMoovin dadosTransporteMoovin;
    @JsonProperty("dados_entrega")
    DadosEntrega dadosEntrega;
}
