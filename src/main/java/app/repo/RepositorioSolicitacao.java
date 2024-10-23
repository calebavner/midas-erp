package app.repo;

import app.domain.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositorioSolicitacao extends JpaRepository<Solicitacao, UUID> {
}
