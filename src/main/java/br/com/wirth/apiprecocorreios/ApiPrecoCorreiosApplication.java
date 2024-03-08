package br.com.wirth.apiprecocorreios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiPrecoCorreiosApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiPrecoCorreiosApplication.class, args);
    }
}
