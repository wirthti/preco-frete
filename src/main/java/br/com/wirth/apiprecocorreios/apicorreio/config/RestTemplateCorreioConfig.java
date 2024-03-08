package br.com.wirth.apiprecocorreios.apicorreio.config;

import br.com.wirth.apiprecocorreios.apicorreio.security.CorreioBearerTokenInterceptor;
import br.com.wirth.apiprecocorreios.services.CorreioTokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateCorreioConfig {
    private final RestTemplateBuilder restTemplateBuilder;
    private final CorreioTokenService correioService;

    public RestTemplateCorreioConfig(RestTemplateBuilder restTemplateBuilder, CorreioTokenService correioService) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.correioService = correioService;
    }

    @Bean
    @Qualifier("restTemplateCorreio")
    public RestTemplate restTemplateCorreio(){
        return this.restTemplateBuilder.additionalInterceptors(new CorreioBearerTokenInterceptor(correioService)).build();
    }


}
