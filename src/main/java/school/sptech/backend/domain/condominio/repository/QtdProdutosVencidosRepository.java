package school.sptech.backend.domain.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.condominio.view.QtdProdutosVencidos;

public interface QtdProdutosVencidosRepository extends JpaRepository<QtdProdutosVencidos, Integer> {
}
