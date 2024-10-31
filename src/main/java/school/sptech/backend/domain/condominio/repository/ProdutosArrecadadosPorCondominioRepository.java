package school.sptech.backend.domain.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.condominio.view.ProdutosArrecadadosPorCondominio;

import java.util.List;

public interface ProdutosArrecadadosPorCondominioRepository extends JpaRepository<ProdutosArrecadadosPorCondominio, Integer> {
    List<ProdutosArrecadadosPorCondominio> findByCondominioId(Integer id);
}
