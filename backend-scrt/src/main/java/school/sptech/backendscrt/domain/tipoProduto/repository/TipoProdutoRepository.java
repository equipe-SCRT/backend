package school.sptech.backendscrt.domain.tipoProduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backendscrt.domain.tipoProduto.TipoProduto;

import java.util.Optional;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Integer> {
    Optional<TipoProduto> deleteById(int id);
}
