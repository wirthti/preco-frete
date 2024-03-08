package br.com.wirth.apiprecocorreios.apicorreio.objetos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ObjetoRastro {
    @JsonProperty("largura")
    private String largura;

    @JsonProperty("comprimento")
    private String comprimento;

    @JsonProperty("altura")
    private String altura;

    @JsonProperty("peso")
    private String peso;
}
