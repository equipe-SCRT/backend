package school.sptech.backend.domain.metrica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.backend.domain.metrica.Metrica;

public interface MetricaRepository extends JpaRepository<Metrica, Integer> {
    
}
