package br.com.wirth.apiprecocorreios.services;

import br.com.wirth.apiprecocorreios.moovin.AtributosMoovin;
import br.com.wirth.apiprecocorreios.moovin.PedidoMoovin;
import br.com.wirth.apiprecocorreios.moovin.RetornoMoovin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static br.com.wirth.apiprecocorreios.constants.Constants.API_MOOVIN;
import static br.com.wirth.apiprecocorreios.constants.Constants.TOKEN_MOOVIN;

@Service
public class MoovinService {

    public List<PedidoMoovin> buscaDadosPorStatusEData(AtributosMoovin atributosMoovin) throws Exception {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", TOKEN_MOOVIN);

        RestTemplate restTemplateMoovin = new RestTemplate();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(atributosMoovin);

       /* MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("token", TOKEN_MOOVIN);
        map.add("data", json);*/

        final HttpEntity<String> request = new HttpEntity<>(json, headers);
        try {
            ResponseEntity<RetornoMoovin> result = restTemplateMoovin.postForEntity(API_MOOVIN, request, RetornoMoovin.class);
            if (result.getBody() != null) {
                return Objects.requireNonNull(result.getBody()).getPedidosMoovin().stream().filter(x -> x.getDadosTransporteMoovin().getCodigo_transportadora().equals("1") || x.getDadosTransporteMoovin().getCodigo_transportadora().equals("4")).collect(Collectors.toList());
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }
}
