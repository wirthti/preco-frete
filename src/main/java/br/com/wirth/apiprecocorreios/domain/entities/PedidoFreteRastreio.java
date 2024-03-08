package br.com.wirth.apiprecocorreios.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PEDIDOFRETERASTREIO")
public class PedidoFreteRastreio implements Serializable {

    @Serial
    private static final long serialVersionUID = 11465584917L;

    @Id
    @Column(name = "PEDIDO", nullable = false)
    private String pedido;

    @Column(name = "COD_RASTREIO", nullable = false)
    private String codRastreio;

    @Column(name = "FRETE_COBRADO")
    private Double freteCobrado;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoFreteRastreio that)) return false;
        return Objects.equals(pedido, that.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido);
    }
}
