package school.sptech.backend.domain.tipoproduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.tipoproduto.TipoProduto;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Integer> {

}
