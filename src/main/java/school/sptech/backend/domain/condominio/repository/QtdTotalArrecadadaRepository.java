package school.sptech.backend.domain.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.condominio.view.QtdTotalArrecadada;

import java.util.List;

public interface QtdTotalArrecadadaRepository extends JpaRepository<QtdTotalArrecadada, Integer> {
    List<QtdTotalArrecadada> findAllByProdutoId(Integer id);
}
