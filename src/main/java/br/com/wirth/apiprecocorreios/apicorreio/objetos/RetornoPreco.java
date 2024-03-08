package br.com.wirth.apiprecocorreios.apicorreio.objetos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RetornoPreco {
    @JsonProperty("pcFinal")
    private String pcFinal;
}
