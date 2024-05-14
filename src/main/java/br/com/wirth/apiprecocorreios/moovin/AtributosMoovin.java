package br.com.wirth.apiprecocorreios.moovin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AtributosMoovin implements Serializable {

    @JsonProperty("data_de")
    private String data_de = LocalDateTime.now().minusDays(30).toString();
    @JsonProperty("data_ate")
    private String data_ate = LocalDateTime.now().toString();
    @JsonProperty("codigo_status_pedido")
    private Integer codigo_status_pedido;

    public AtributosMoovin(Integer codigo_status) {
        this.codigo_status_pedido = codigo_status;
    }
}
