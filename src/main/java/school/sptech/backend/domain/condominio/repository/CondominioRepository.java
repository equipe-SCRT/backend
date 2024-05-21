package school.sptech.backend.domain.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.condominio.Condominio;

import java.util.Optional;

public interface CondominioRepository extends JpaRepository<Condominio, Integer> {

    Optional<Condominio> findByNomeIgnoreCase(String nome);
}
