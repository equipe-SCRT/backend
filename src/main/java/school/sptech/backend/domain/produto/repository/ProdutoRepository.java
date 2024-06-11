package school.sptech.backend.domain.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.produto.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
