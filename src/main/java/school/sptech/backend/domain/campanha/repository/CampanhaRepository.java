package school.sptech.backend.domain.campanha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.campanha.Campanha;

public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {
}
