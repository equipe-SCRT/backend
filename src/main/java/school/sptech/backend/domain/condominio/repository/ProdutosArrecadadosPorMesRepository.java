package school.sptech.backend.domain.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.condominio.view.ProdutosArrecadadosPorMes;

import java.time.LocalDate;
import java.util.List;

public interface ProdutosArrecadadosPorMesRepository extends JpaRepository<ProdutosArrecadadosPorMes, Integer> {
    List<ProdutosArrecadadosPorMes> findAllByCriadoEmBetween(LocalDate inicio, LocalDate fim);
}
