package school.sptech.backendscrt.domain.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backendscrt.domain.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
