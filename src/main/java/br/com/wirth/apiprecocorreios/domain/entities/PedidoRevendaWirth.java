package br.com.wirth.apiprecocorreios.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PEDIDOREVENDA")
public class PedidoRevendaWirth implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PedidoRevendaID id;

    @Column(name = "PEDIDOCLIENTE")
    private String pedidocliente;

    @Column(name = "CPESSOA", nullable = false)
    private String cpessoa;

    @Column(name = "CPESSOAENTREGA", nullable = false)
    private String cpessoaentrega;

    @Column(name = "DATAPEDIDO", nullable = false)
    private Date datapedido;

    @Column(name = "DATAENTREGA", nullable = false)
    private Date dataentrega;

    @Column(name = "CREPRESENTANTE", nullable = false)
    private String crepresentante;

    @Column(name = "CCONDPAG")
    private String ccondpag;

    @Column(name = "CSITUACAO", nullable = false)
    private String csituacao;

    @Column(name = "COMISSAO", nullable = false)
    private String comissao;

    @Column(name = "CSEGMENTOMERCADO", nullable = false)
    private String csegmentomercado;

    @Column(name = "QTDE", nullable = false)
    private String qtde;

    @Column(name = "CUNIDADE", nullable = false)
    private String cunidade;

    @Column(name = "VALORUNITARIO", nullable = false)
    private String valorunitario;

    @Column(name = "VALORTOTAL")
    private String valortotal;

    @Column(name = "DATAFATURAR")
    private Date datafaturar;

    @Column(name = "OBS")
    private String obs;

    @Column(name = "CESPECIALIZACAO")
    private String cespecializacao;

    @Column(name = "FRETE", nullable = false)
    private String frete;

    @Column(name = "PARCELAS", nullable = false)
    private String parcelas;

    @Column(name = "CMATERIAL")
    private String cmaterial;

    @Column(name = "TRANSPORTADORA")
    private String transportadora;

    @Column(name = "CANCELADO", nullable = false)
    private String cancelado;

    @Column(name = "GATEWAY_TRANSACAO_ID")
    private String gatewayTransacaoId;

    @Column(name = "PEDIDO_MKTPLACE")
    private String pedidoMktplace;

    @Column(name = "FORMA_PAGAMENTO")
    private String formaPagamento;

    @Column(name = "NUM_AUTORIZACAO")
    private String numAutorizacao;

}
