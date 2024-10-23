package app.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_solicitacoes")
public class Solicitacao {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idSolicitacao;

    private String dataSolicitacao;

    @OneToMany
    private List<Produto> produtos = new ArrayList<>();
    private BigDecimal totalSolicatao;
    private StatusSolicitacao statusSolicitacao;

    public Solicitacao() {
    }

    public Solicitacao(String dataSolicitacao, List<Produto> produtos, BigDecimal totalSolicatao) {
        this.dataSolicitacao = dataSolicitacao;
        this.produtos = produtos;
        this.totalSolicatao = totalSolicatao;
    }

    public UUID getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(UUID idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public void adicionaProduto(Produto p) {
        this.produtos.add(p);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public BigDecimal getTotalSolicatao() {
        return totalSolicatao;
    }

    public void setTotalSolicatao(BigDecimal totalSolicatao) {
        this.totalSolicatao = totalSolicatao;
    }

    public StatusSolicitacao getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }
}
