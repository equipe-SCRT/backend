package school.sptech.backend.domain.produtounitario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.produtounitario.view.QtdProdutoPorCampanha;

import java.util.List;

public interface QtdProdutoPorCampanhaRepository extends JpaRepository<QtdProdutoPorCampanha, Integer> {
    List<QtdProdutoPorCampanha> findByProdutoId(Integer id);
}
