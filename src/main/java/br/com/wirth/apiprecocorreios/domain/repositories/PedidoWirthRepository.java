package br.com.wirth.apiprecocorreios.domain.repositories;

import br.com.wirth.apiprecocorreios.domain.entities.PedidoWirth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PedidoWirthRepository extends JpaRepository<PedidoWirth, String> {

    List<PedidoWirth> findAllByPedidoclienteInAndCcompanhiaAndCtipopedidoAndPedidoNotIn(Collection<String> pedidocliente, String ccompanhia, String ctipopedido, Collection<String> pedido);

    List<PedidoWirth>findAllByPedidoclienteInAndCcompanhiaAndCtipopedido(Collection<String> pedidocliente, String ccompanhia, String ctipopedido);
}