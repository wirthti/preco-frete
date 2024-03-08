package br.com.wirth.apiprecocorreios.apicorreio.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RetornoTokenCorreio {
    @JsonProperty("token")
    private String token;
    @JsonProperty("expiraEm")
    private String expiraEm;
}
