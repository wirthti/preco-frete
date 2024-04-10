package br.com.wirth.apiprecocorreios.services;

import br.com.wirth.apiprecocorreios.apicorreio.objetos.RetornoPreco;
import br.com.wirth.apiprecocorreios.apicorreio.objetos.RetornoRastro;
import br.com.wirth.apiprecocorreios.constants.Constants;
import br.com.wirth.apiprecocorreios.domain.entities.PedidoFreteRastreio;
import br.com.wirth.apiprecocorreios.domain.entities.PedidoRevendaWirth;
import br.com.wirth.apiprecocorreios.domain.entities.PedidoWirth;
import br.com.wirth.apiprecocorreios.domain.repositories.PedidoFreteRastreioRepository;
import br.com.wirth.apiprecocorreios.domain.repositories.PedidoRevendaWirthRepository;
import br.com.wirth.apiprecocorreios.domain.repositories.PedidoWirthRepository;
import br.com.wirth.apiprecocorreios.moovin.AtributosMoovin;
import br.com.wirth.apiprecocorreios.moovin.PedidoMoovin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.wirth.apiprecocorreios.constants.Constants.*;

@Slf4j
@Service
public class CorreioService {
    private final RestTemplate restTemplateCorreio;
    private final MoovinService moovinService;
    private final PedidoWirthRepository pedidoWirthRepository;
    private final PedidoRevendaWirthRepository pedidoRevendaWirthRepository;
    private final PedidoFreteRastreioRepository pedidoFreteRastreioRepository;

    public CorreioService(@Qualifier("restTemplateCorreio") RestTemplate restTemplateCorreio, MoovinService moovinService, PedidoWirthRepository pedidoWirthRepository, PedidoRevendaWirthRepository pedidoRevendaWirthRepository, PedidoFreteRastreioRepository pedidoFreteRastreioRepository) {
        this.restTemplateCorreio = restTemplateCorreio;
        this.moovinService = moovinService;
        this.pedidoWirthRepository = pedidoWirthRepository;
        this.pedidoRevendaWirthRepository = pedidoRevendaWirthRepository;
        this.pedidoFreteRastreioRepository = pedidoFreteRastreioRepository;
    }

    //@Scheduled(cron = "0 30 01 * * *")
    //@Scheduled(fixedDelay = 1000000000L)
    @Scheduled(cron = "0 30 01,13 * * *")
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void rotinaMaryJaneMoovin() throws Exception {

        List<PedidoFreteRastreio> pedidoFreteRastreioList = pedidoFreteRastreioRepository.findAllByDataAtualizacaoAfter(LocalDateTime.now().minusDays(7));

        List<PedidoMoovin> retornoMoovinTransporte = this.moovinService.buscaDadosPorStatusEData(new AtributosMoovin(17));
        List<PedidoMoovin> retornoMoovinEntregue = this.moovinService.buscaDadosPorStatusEData(new AtributosMoovin(14));
        List<PedidoMoovin> retornoMoovin = Stream.concat(retornoMoovinEntregue.stream(), retornoMoovinTransporte.stream()).toList();

        List<PedidoWirth> pedidosWirth;
        List<PedidoRevendaWirth> pedidosRevendaWirth;

        if (pedidoFreteRastreioList.isEmpty()){
            pedidosWirth = this.pedidoWirthRepository.findAllByPedidoclienteInAndCcompanhiaAndCtipopedido(
                    retornoMoovin.stream().map(PedidoMoovin::getCodigo_pedido).collect(Collectors.toList()),
                    "192", "14"
            );

            pedidosRevendaWirth = this.pedidoRevendaWirthRepository.findAllByPedidoclienteIn(
                    retornoMoovin.stream().map(PedidoMoovin::getCodigo_pedido).collect(Collectors.toList())
            );
        }
        else {
            pedidosWirth = this.pedidoWirthRepository.findAllByPedidoclienteInAndCcompanhiaAndCtipopedidoAndPedidoNotIn(
                    retornoMoovin.stream().map(PedidoMoovin::getCodigo_pedido).collect(Collectors.toList()),
                    "192", "14",
                    pedidoFreteRastreioList.stream().map(PedidoFreteRastreio::getPedido).collect(Collectors.toList())
            );

            pedidosRevendaWirth = this.pedidoRevendaWirthRepository.buscaAllByPedidoclienteInAndPedidoNotIn(
                    retornoMoovin.stream().map(PedidoMoovin::getCodigo_pedido).collect(Collectors.toList()),
                    pedidoFreteRastreioList.stream().map(PedidoFreteRastreio::getPedido).collect(Collectors.toList())
            );
        }

        List<PedidoMoovin> pedidosSemFrete = new ArrayList<>();
        for (PedidoMoovin pedidoMoovin : retornoMoovin) {
            if (pedidosWirth.stream().anyMatch(pedidoWirth -> pedidoWirth.getPedidocliente().equals(pedidoMoovin.getCodigo_pedido()))) {
                pedidosSemFrete.add(pedidoMoovin);
            } else if (pedidosRevendaWirth.stream().anyMatch(pedidoWirth -> pedidoWirth.getPedidocliente().equals(pedidoMoovin.getCodigo_pedido()))) {
                pedidosSemFrete.add(pedidoMoovin);
            }
        }

        for (PedidoMoovin pedidoMoovin : pedidosSemFrete) {
            ResponseEntity<RetornoRastro> retornoRastro = this.restTemplateCorreio.getForEntity(API_BASE + API_RASTRO + pedidoMoovin.getDadosTransporteMoovin().getCodigo_rastreio(), RetornoRastro.class);
            if (retornoRastro.getStatusCode() == HttpStatus.OK) {
                RetornoRastro dadosRastro = retornoRastro.getBody();
                if (dadosRastro != null && dadosRastro.getObjetos().getFirst().getPeso() != null) {
                    String urlTemplate = UriComponentsBuilder.fromHttpUrl(API_BASE + API_PRECO + pedidoMoovin.getDadosTransporteMoovin().getCodigo_servico())
                            .queryParam("coProduto", "{coProduto}")
                            .queryParam("cepDestino", "{cepDestino}")
                            .queryParam("cepOrigem", "{cepOrigem}")
                            .queryParam("nuContrato", "{nuContrato}")
                            .queryParam("nuDR", "{nuDR}")
                            .queryParam("psObjeto", "{psObjeto}")
                            .queryParam("tpObjeto", "{tpObjeto}")
                            .queryParam("comprimento", "{comprimento}")
                            .queryParam("largura", "{largura}")
                            .queryParam("altura", "{altura}")
                            .encode()
                            .toUriString();

                    Map<String, String> params = populaParametros(pedidoMoovin, dadosRastro);

                    log.info("Buscando frete do pedido " + pedidoMoovin.getCodigo_pedido() + ", CEP " + pedidoMoovin.getDadosEntrega().getCep());
                    HttpHeaders headers = new HttpHeaders();
                    HttpEntity<?> entity = new HttpEntity<>(headers);
                    ResponseEntity<RetornoPreco> retornoPreco = this.restTemplateCorreio.exchange(urlTemplate, HttpMethod.GET, entity, RetornoPreco.class, params);
                    if (retornoPreco.getStatusCode() == HttpStatus.OK) {
                        RetornoPreco dadosPreco = retornoPreco.getBody();
                        if (dadosPreco != null) {
                            Optional<String> numPedidoOptional = pedidosWirth.stream()
                                    .filter(pedidoWirth -> pedidoWirth.getPedidocliente().equals(pedidoMoovin.getCodigo_pedido()))
                                    .map(PedidoWirth::getPedido)
                                    .findFirst();
                            if (numPedidoOptional.isEmpty()) {
                                numPedidoOptional = pedidosRevendaWirth.stream()
                                        .filter(pedidoWirth -> pedidoWirth.getPedidocliente().equals(pedidoMoovin.getCodigo_pedido()))
                                        .map(item -> item.getId().getPedido())
                                        .findFirst();
                            }
                            if (numPedidoOptional.isEmpty()) {
                                throw new RuntimeException("Erro meu amiguinho");
                            } else {
                                String numPedido = numPedidoOptional.get();
                                PedidoFreteRastreio pedidoFreteRastreio = new PedidoFreteRastreio(numPedido, pedidoMoovin.getDadosTransporteMoovin().getCodigo_rastreio(),
                                        Double.parseDouble(dadosPreco.getPcFinal().replace(',', '.')), LocalDateTime.now());
                                this.pedidoFreteRastreioRepository.save(pedidoFreteRastreio);
                                log.info("Registro inserido: " + pedidoFreteRastreio.getPedido() + ", frete: " + pedidoFreteRastreio.getFreteCobrado() + "\n");
                            }
                        }
                    }
                }
            }
        }
    }

    private static Map<String, String> populaParametros(PedidoMoovin pedido, RetornoRastro dadosRastro) {
        Map<String, String> params = new HashMap<>();
        params.put("coProduto", pedido.getDadosTransporteMoovin().getCodigo_servico());
        params.put("cepDestino", pedido.getDadosEntrega().getCep().replace("-", ""));
        params.put("cepOrigem", Constants.CORREIOS_CEP_ORIGEM);
        params.put("nuContrato", Constants.CORREIOS_NR_CONTRATO);
        params.put("nuDR", Constants.CORREIOS_DR_CONTRATO);
        // Peso x1000 para que o parâmetro seja enviado em gramas
        params.put("psObjeto", String.valueOf(Double.parseDouble(dadosRastro.getObjetos().getFirst().getPeso().replace(',', '.')) * 1000));
        params.put("tpObjeto", "2");
        params.put("comprimento", dadosRastro.getObjetos().getFirst().getComprimento());
        params.put("largura", dadosRastro.getObjetos().getFirst().getLargura());
        params.put("altura", dadosRastro.getObjetos().getFirst().getAltura());
        return params;
    }

}
