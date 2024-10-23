package app.controller;

import app.domain.ProdutoSolicitacao;
import app.domain.Solicitacao;
import app.service.ServiceSolicitacao;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/solicitacoes")
public class ControllerSolicitacao {

    private final ServiceSolicitacao serviceSolicitacao;

    public ControllerSolicitacao(ServiceSolicitacao serviceSolicitacao) {
        this.serviceSolicitacao = serviceSolicitacao;
    }

    @PostMapping("/nova")
    public ResponseEntity<Solicitacao> novaSolicitacao() throws Exception{
        return ResponseEntity.ok(serviceSolicitacao.novaSolicitacao());
    }

    @PutMapping
    public ResponseEntity<Solicitacao> adicionarProduto(@RequestBody ProdutoSolicitacao produtoSolicitacao) {
        return ResponseEntity.ok(serviceSolicitacao.adicionarProduto(produtoSolicitacao));
    }

    @GetMapping("/{idSolicitacao}")
    public ResponseEntity<Solicitacao> buscarSolicitacao(@PathVariable UUID idSolicitacao) {
        return ResponseEntity.ok(serviceSolicitacao.buscarSolicitacao(idSolicitacao));
    }

    @PostMapping("/{idSolicitacao}")
    public ResponseEntity<String> enviarParaAprovacao(@PathVariable UUID idSolicitacao) throws JsonProcessingException {
        return ResponseEntity.ok(serviceSolicitacao.enviarParaAprovacao(idSolicitacao));
    }
}
