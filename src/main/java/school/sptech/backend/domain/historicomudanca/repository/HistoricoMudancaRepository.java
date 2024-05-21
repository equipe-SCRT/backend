package school.sptech.backend.domain.historicomudanca.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import school.sptech.backend.domain.historicomudanca.HistoricoMudanca;

public interface HistoricoMudancaRepository extends JpaRepository<HistoricoMudanca, Integer> {
    
}
