package br.com.wirth.apiprecocorreios.apicorreio.objetos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static br.com.wirth.apiprecocorreios.constants.Constants.CODIGO_POSTAL;

@Data
public class CartaoPostalCorreio {
    @JsonProperty("numero")
    private String numero = CODIGO_POSTAL;
}
