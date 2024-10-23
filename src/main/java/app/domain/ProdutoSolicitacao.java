package app.domain;

import java.util.UUID;

public record ProdutoSolicitacao(

        UUID idSolicitacao,
        Long idProduto,
        int quantidade
) {
}
