package br.com.wirth.apiprecocorreios.domain.repositories;

import br.com.wirth.apiprecocorreios.domain.entities.PedidoRevendaID;
import br.com.wirth.apiprecocorreios.domain.entities.PedidoRevendaWirth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PedidoRevendaWirthRepository extends JpaRepository<PedidoRevendaWirth, PedidoRevendaID> {

    //List<PedidoRevendaWirth> findAllByPedidoclienteInAndPedidoNotIn(Collection<String> pedidosCliente, Collection<String> pedidos);

    @Query(value = """
        select p
          from PedidoRevendaWirth p
         where p.pedidocliente in ?1
           and p.id.pedido not in ?2
    """)
    List<PedidoRevendaWirth> buscaAllByPedidoclienteInAndPedidoNotIn(final Collection<String> pedidosCliente, final Collection<String> pedidos);
    List<PedidoRevendaWirth> findAllByPedidoclienteIn(Collection<String> pedidosCliente);

}