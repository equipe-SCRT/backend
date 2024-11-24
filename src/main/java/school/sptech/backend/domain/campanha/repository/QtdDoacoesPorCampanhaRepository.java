package school.sptech.backend.domain.campanha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.campanha.view.QtdDoacoesPorCampanha;

import java.time.LocalDate;
import java.util.List;

public interface QtdDoacoesPorCampanhaRepository extends JpaRepository<QtdDoacoesPorCampanha, Integer> {
    List<QtdDoacoesPorCampanha> findByNomeAndDataCampanhaBetween(String nome, LocalDate inicio, LocalDate fim);
}
