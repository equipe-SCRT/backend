package school.sptech.backend.domain.produtounitario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.backend.service.produtounitario.view.QtdVencidoPorMes;

import java.time.LocalDate;
import java.util.List;

public interface QtdVencidoMesRepository extends JpaRepository<QtdVencidoPorMes, Integer> {

    List<QtdVencidoPorMes> findByProdutoIdAndDataValidadeBetween(Integer id, LocalDate inicio, LocalDate fim);

    @Query(
            "SELECT SUM(q.qtd)" +
                    " FROM QtdVencidoPorMes q" +
                    " WHERE q.dataValidade between :inicio AND :fim"
    )
    Integer sumByDataValidadeBetween(LocalDate inicio, LocalDate fim);

}
