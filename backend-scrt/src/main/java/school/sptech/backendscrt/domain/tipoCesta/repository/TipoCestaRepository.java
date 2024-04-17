package school.sptech.backendscrt.domain.tipoCesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backendscrt.domain.tipoCesta.TipoCesta;

import java.util.Optional;

public interface TipoCestaRepository extends JpaRepository<TipoCesta, Integer> {
    Optional<TipoCesta> deleteById(int id);
}
