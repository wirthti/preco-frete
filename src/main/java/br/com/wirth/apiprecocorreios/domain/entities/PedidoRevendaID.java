package br.com.wirth.apiprecocorreios.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
public class PedidoRevendaID implements Serializable {

    private String pedido;
    private String cmaterialcombo;
    private String item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoRevendaID that)) return false;
        return Objects.equals(pedido, that.pedido) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, item);
    }
}
