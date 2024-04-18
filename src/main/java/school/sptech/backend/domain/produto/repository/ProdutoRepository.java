package school.sptech.backend.domain.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.produto.Produto;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Optional<Produto> deleteById(int id);
}
