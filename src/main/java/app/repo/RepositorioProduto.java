package app.repo;

import app.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioProduto extends JpaRepository<Produto, Long> {
}
