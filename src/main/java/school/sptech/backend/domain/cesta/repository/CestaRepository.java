package school.sptech.backend.domain.cesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.cesta.Cesta;

public interface CestaRepository extends JpaRepository<Cesta, Integer> {
}
