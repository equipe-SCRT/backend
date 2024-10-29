package school.sptech.backend.domain.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.condominio.view.ProdutosArrecadadosPorCondominio;

public interface ProdutosArrecadadosPorCondominioRepository extends JpaRepository<ProdutosArrecadadosPorCondominio, Integer> {
}
