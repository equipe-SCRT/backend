package school.sptech.backend.domain.rota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.rota.Rota;

import java.util.Optional;

public interface RotaRepository extends JpaRepository<Rota, Integer> {

    Optional<Rota> findByNomeIgnoreCase(String nome);
}
