package br.com.wirth.apiprecocorreios.moovin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DadosEntrega {
    @JsonProperty("cep")
    private String cep;
}
