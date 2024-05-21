package school.sptech.backend.domain.produtounitario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;

public interface ProdutoUnitarioRepository extends JpaRepository<ProdutoUnitario, Integer> {
}
