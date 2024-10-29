package school.sptech.backend.domain.campanha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.campanha.Campanha;

import java.time.LocalDate;
import java.util.List;

public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {
    List<Campanha> findByDataCampanha(LocalDate data);
}
