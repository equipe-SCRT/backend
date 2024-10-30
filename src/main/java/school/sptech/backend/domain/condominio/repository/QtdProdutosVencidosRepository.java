package school.sptech.backend.domain.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.condominio.view.QtdProdutosVencidos;

import java.util.List;

public interface QtdProdutosVencidosRepository extends JpaRepository<QtdProdutosVencidos, Integer> {
    List<QtdProdutosVencidos> findByCondominioId(Integer id);
}
