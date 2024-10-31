package school.sptech.backend.domain.produtocesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.produtocesta.ProdutoCesta;

import java.util.List;

public interface ProdutoCestaRepository extends JpaRepository<ProdutoCesta, Integer> {

    List<ProdutoCesta> findByTipoCestaId(Integer tipoCestaId);
}
