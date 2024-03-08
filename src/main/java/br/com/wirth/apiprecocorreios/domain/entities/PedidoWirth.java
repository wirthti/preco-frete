package br.com.wirth.apiprecocorreios.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PEDIDOS")
public class PedidoWirth implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PEDIDO", nullable = false)
    private String pedido;

    @Column(name = "CPESSOAJUR", nullable = false)
    private String cpessoajur;

    @Column(name = "DATAPEDIDO", nullable = false)
    private Date datapedido;

    @Column(name = "MERCADOIE", nullable = false)
    private String mercadoie;

    @Column(name = "DATAENTREGA", nullable = false)
    private Date dataentrega;

    @Column(name = "CREPRESEN", nullable = false)
    private String crepresen;

    @Column(name = "COMISSAO", nullable = false)
    private String comissao;

    @Column(name = "CCONDPAG", nullable = false)
    private String ccondpag;

    @Column(name = "OBS")
    private String obs;

    @Column(name = "PARES")
    private String pares;

    @Column(name = "CAIXAINDMI")
    private String caixaindmi;

    @Column(name = "CARIMBOSOLA")
    private String carimbosola;

    @Column(name = "CARIMBOCORRUGADO")
    private String carimbocorrugado;

    @Column(name = "PEDIDOCLIENTE")
    private String pedidocliente;

    @Column(name = "CETIQUETA")
    private String cetiqueta;

    @Column(name = "CMOEDA", nullable = false)
    private String cmoeda;

    @Column(name = "CMODELISTAT")
    private String cmodelistat;

    @Column(name = "CMODELISTAC")
    private String cmodelistac;

    @Column(name = "CMODELISTAP")
    private String cmodelistap;

    @Column(name = "CMODELISTAD")
    private String cmodelistad;

    @Column(name = "IDLOTE")
    private String idlote;

    @Column(name = "MODTIPORELCOMP", nullable = false)
    private String modtiporelcomp;

    @Column(name = "CANCELADO")
    private Date cancelado;

    @Column(name = "CLOGO")
    private String clogo;

    @Column(name = "CTIPOPEDIDO", nullable = false)
    private String ctipopedido;

    @Column(name = "LIBERACAOPRECO", nullable = false)
    private String liberacaopreco;

    @Column(name = "CONSIGNED")
    private String consigned;

    @Column(name = "PEDIDOREPRES")
    private String pedidorepres;

    @Column(name = "CCIDADEDESTMERC")
    private String ccidadedestmerc;

    @Column(name = "CPESSOATRAN")
    private String cpessoatran;

    @Column(name = "CCOMPANHIA", nullable = false)
    private String ccompanhia;

    @Column(name = "DATAPEDIDOREP")
    private Date datapedidorep;

    @Column(name = "CVENDEDOR")
    private String cvendedor;

    @Column(name = "EMBALAGEM")
    private String embalagem;

    @Column(name = "CPESSOAENTREGA")
    private String cpessoaentrega;

    @Column(name = "CCOLECAO")
    private String ccolecao;

    @Column(name = "INCLUIREFERCLIENTENFE")
    private String incluireferclientenfe;

    @Column(name = "LERESTATISTICAVENDA", nullable = false)
    private String lerestatisticavenda;

    @Column(name = "LERPECOMOPRODUCAO", nullable = false)
    private String lerpecomoproducao;

    @Column(name = "DESCONTO")
    private String desconto;

    @Column(name = "FRETE", nullable = false)
    private String frete;

    @Column(name = "PARCELAS", nullable = false)
    private String parcelas;

    @Column(name = "TRANSPORTADORA")
    private String transportadora;

    @Column(name = "LERCONTROLEFINANCEIRO", nullable = false)
    private String lercontrolefinanceiro;

    @Column(name = "LERPEPROGRAMADA", nullable = false)
    private String lerpeprogramada;

    @Column(name = "OBSFATURAR")
    private String obsfaturar;

    @Column(name = "ESTACAO_CLIENTE")
    private String estacaoCliente;

    @Column(name = "TAXA_CAMBIO")
    private String taxaCambio;

    @Column(name = "GATEWAY_TRANSACAO_ID")
    private String gatewayTransacaoId;

    @Column(name = "PEDIDO_MKTPLACE")
    private String pedidoMktplace;

    @Column(name = "PEDIDO_MASTER")
    private String pedidoMaster;

    @Column(name = "CONTROLAR_SALDO_CONTRATO", nullable = false)
    private String controlarSaldoContrato;

    @Column(name = "TAXA_NEGOCIACAO_CLIENTE")
    private String taxaNegociacaoCliente;

    @Column(name = "VENDA_EQUIPARADA", nullable = false)
    private String vendaEquiparada;

    @Column(name = "FORMA_PAGAMENTO")
    private String formaPagamento;

    @Column(name = "NUM_AUTORIZACAO")
    private String numAutorizacao;

    @Column(name = "LIBERADO_FATURAMENTO", nullable = false)
    private String liberadoFaturamento;

}
