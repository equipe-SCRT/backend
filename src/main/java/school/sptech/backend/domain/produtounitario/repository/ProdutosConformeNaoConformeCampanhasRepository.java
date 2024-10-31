package school.sptech.backend.domain.produtounitario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.produtounitario.view.ProdutosConformeNaoConformeCampanhas;

import java.util.List;

public interface ProdutosConformeNaoConformeCampanhasRepository extends JpaRepository<ProdutosConformeNaoConformeCampanhas, Integer> {
    List<ProdutosConformeNaoConformeCampanhas> findTop4ByOrderByNaoConformeDesc();
}
