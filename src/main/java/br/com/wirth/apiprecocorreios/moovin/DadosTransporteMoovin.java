package br.com.wirth.apiprecocorreios.moovin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DadosTransporteMoovin implements Serializable {
    @JsonProperty("codigo_rastreio")
    private String codigo_rastreio;
    @JsonProperty("codigo_servico")
    private String codigo_servico;
    @JsonProperty("codigo_transportadora")
    private String codigo_transportadora;
}
