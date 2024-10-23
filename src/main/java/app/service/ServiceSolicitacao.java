package app.service;

import app.domain.Produto;
import app.domain.ProdutoSolicitacao;
import app.domain.Solicitacao;
import app.domain.StatusSolicitacao;
import app.repo.RepositorioProduto;
import app.repo.RepositorioSolicitacao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ServiceSolicitacao {

    private final String SQS_URL = "https://localhost.localstack.cloud:4566/000000000000/fila-solicitacoes";
    private final SqsTemplate sqsTemplate;
    private final RepositorioSolicitacao repoSolicitacao;
    private final RepositorioProduto repoProduto;

    public ServiceSolicitacao(RepositorioSolicitacao repoSolicitacao, RepositorioProduto repoProduto, SqsTemplate sqsTemplate) {
        this.repoSolicitacao = repoSolicitacao;
        this.repoProduto = repoProduto;
        this.sqsTemplate = sqsTemplate;
    }

    public Solicitacao novaSolicitacao() throws Exception{

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = LocalDate.now().format(fmt);

        Solicitacao s = new Solicitacao();
        s.setDataSolicitacao(data);
        s.setTotalSolicatao(BigDecimal.ZERO);
        s.setStatusSolicitacao(StatusSolicitacao.AGUARDANDO_APROVACAO);
        return repoSolicitacao.save(s);
    }

    public Solicitacao adicionarProduto(ProdutoSolicitacao produtoSolicitacao) {

        Produto p = repoProduto.findById(produtoSolicitacao.idProduto())
                .orElseThrow(() -> new EntityNotFoundException());

        BigDecimal total = p.getValor()
                .multiply(BigDecimal.valueOf(produtoSolicitacao.quantidade()));


        Solicitacao s = repoSolicitacao.findById(produtoSolicitacao.idSolicitacao())
                .orElseThrow(() -> new EntityNotFoundException());

        s.adicionaProduto(p);
        s.setTotalSolicatao(s.getTotalSolicatao().add(total));

        return repoSolicitacao.save(s);
    }

    public Solicitacao buscarSolicitacao(UUID idSolicitacao) {
        return repoSolicitacao.findById(idSolicitacao)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public String enviarParaAprovacao(UUID idSolicitacao) throws JsonProcessingException {

        Solicitacao s = repoSolicitacao.findById(idSolicitacao)
                .orElseThrow(() -> new EntityNotFoundException());

        String json = new ObjectMapper().writeValueAsString(s);
        sqsTemplate.send(SQS_URL, json);

        return "Sua solicitacao foi enviada para aprovação";
    }
}
