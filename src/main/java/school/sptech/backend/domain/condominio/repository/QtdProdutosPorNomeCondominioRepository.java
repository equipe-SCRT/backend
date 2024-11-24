package school.sptech.backend.domain.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.condominio.view.QtdProdutosPorNomeCondominio;

import java.time.LocalDate;
import java.util.List;

public interface QtdProdutosPorNomeCondominioRepository extends JpaRepository<QtdProdutosPorNomeCondominio, Integer> {
    List<QtdProdutosPorNomeCondominio> findAllByNomeCondominioAndCriadoEmBetween(String nomeCondominio, LocalDate inicio, LocalDate fim);
}
