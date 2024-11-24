package school.sptech.backend.domain.campanha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.campanha.view.AlimentosArrecadadosPorMes;

import java.time.LocalDate;
import java.util.List;

public interface AlimentosArrecadadosPorMesRepository extends JpaRepository<AlimentosArrecadadosPorMes, Integer> {
    List<AlimentosArrecadadosPorMes> findAllByDataCampanhaBetween(LocalDate inicio, LocalDate fim);
}
