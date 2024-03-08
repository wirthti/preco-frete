package br.com.wirth.apiprecocorreios.apicorreio.objetos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RetornoRastro {
    @JsonProperty("objetos")
    List<ObjetoRastro>objetos;
}
