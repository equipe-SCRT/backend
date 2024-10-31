package school.sptech.backend.domain.produtounitario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.produtounitario.view.Vencimento15E30Dias;

public interface Vencimento15E30DiasRepository extends JpaRepository<Vencimento15E30Dias, Integer> {
}
