package app.service;

import app.domain.Produto;
import app.repo.RepositorioProduto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProduto {

    private final RepositorioProduto repoProduto;

    public ServiceProduto(RepositorioProduto repoProduto) {
        this.repoProduto = repoProduto;
    }

    public Produto cadastrarProduto(Produto produto) {
        return repoProduto.save(produto);
    }

    public List<Produto> listarProdutosCadastradados() {
        return repoProduto.findAll();
    }
}
