package br.com.wirth.apiprecocorreios.services;


import br.com.wirth.apiprecocorreios.apicorreio.objetos.CartaoPostalCorreio;
import br.com.wirth.apiprecocorreios.apicorreio.security.RetornoTokenCorreio;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static br.com.wirth.apiprecocorreios.constants.Constants.*;

@Data
@Service
public class CorreioTokenService {
    private String token;
    private LocalDateTime expiraEm;

    public void atualizaToken(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setBasicAuth(USUARIO_CORREIO, SENHA_CORREIO);
        CartaoPostalCorreio cartaoPostalCorreio = new CartaoPostalCorreio();
        HttpEntity<CartaoPostalCorreio>requestTokenCorreio = new HttpEntity<>(cartaoPostalCorreio, header);
        ResponseEntity<RetornoTokenCorreio>retornoToken = restTemplate.postForEntity(API_BASE + API_AUTENTICACAO, requestTokenCorreio, RetornoTokenCorreio.class);

        this.token = Objects.requireNonNull(retornoToken.getBody()).getToken();
        this.expiraEm = LocalDateTime.parse(retornoToken.getBody().getExpiraEm(), DateTimeFormatter.ISO_DATE_TIME);
    }

    public Boolean tokenExpiradoOuAusente(){
        if (this.expiraEm == null){
            return true;
        }
        return this.expiraEm.isBefore(LocalDateTime.now().plusMinutes(5));
    }


}
