package br.com.wirth.apiprecocorreios.domain.repositories;

import br.com.wirth.apiprecocorreios.domain.entities.PedidoFreteRastreio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoFreteRastreioRepository extends JpaRepository<PedidoFreteRastreio, String> {
    List<PedidoFreteRastreio>findAllByDataAtualizacaoAfterAndFreteCobradoIsNull(final LocalDateTime dateTime);
    List<PedidoFreteRastreio>findAllByDataAtualizacaoAfter(final LocalDateTime dateTime);

}
