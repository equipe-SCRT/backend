package school.sptech.backendscrt.domain.origem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.backendscrt.domain.origem.Origem;

import java.util.Optional;

@Repository
public interface OrigemRepository extends JpaRepository<Origem, Integer> {
    Optional<Origem> deleteById(int id);
}
