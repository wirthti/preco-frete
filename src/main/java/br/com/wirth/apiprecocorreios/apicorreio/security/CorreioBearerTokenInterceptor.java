package br.com.wirth.apiprecocorreios.apicorreio.security;

import br.com.wirth.apiprecocorreios.services.CorreioTokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class CorreioBearerTokenInterceptor implements ClientHttpRequestInterceptor {

    private final CorreioTokenService service;

    public CorreioBearerTokenInterceptor(CorreioTokenService service) {
        this.service = service;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        if(! headers.containsKey("Authorization")){
            if (this.service.tokenExpiradoOuAusente()){
                this.service.atualizaToken();
            }
            String tokenValue = "Bearer " + this.service.getToken();
            headers.add("Authorization", tokenValue);
        }
        return execution.execute(request, body);
    }
}
