package br.com.wirth.apiprecocorreios.moovin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RetornoMoovin implements Serializable {
    @JsonProperty("pedidos")
    List<PedidoMoovin> pedidosMoovin;
}
