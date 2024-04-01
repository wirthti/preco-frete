package br.com.wirth.apiprecocorreios.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Embeddable
public class PedidoRevendaID implements Serializable {

    private String pedido;
    private String cmaterialcombo;
    private String item;
}
