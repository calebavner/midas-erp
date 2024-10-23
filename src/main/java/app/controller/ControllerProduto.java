package app.controller;

import app.domain.Produto;
import app.service.ServiceProduto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
public class ControllerProduto {

    private final ServiceProduto serviceProduto;

    public ControllerProduto(ServiceProduto serviceProduto) {
        this.serviceProduto = serviceProduto;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        return ResponseEntity.ok(serviceProduto.cadastrarProduto(produto));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutosCadastrados() {
        return ResponseEntity.ok(serviceProduto.listarProdutosCadastradados());
    }
}
