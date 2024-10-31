package school.sptech.backend.domain.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.condominio.view.QtdProdutosPorNomeCondominio;

import java.util.List;

public interface QtdProdutosPorNomeCondominioRepository extends JpaRepository<QtdProdutosPorNomeCondominio, Integer> {
    List<QtdProdutosPorNomeCondominio> findAllByNomeCondominio(String nomeCondominio);
}
