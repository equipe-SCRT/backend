package school.sptech.backend.domain.produtocesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.produtocesta.entity.ProdutoCesta;

public interface ProdutoCestaRepository extends JpaRepository<ProdutoCesta, Integer> {
}
