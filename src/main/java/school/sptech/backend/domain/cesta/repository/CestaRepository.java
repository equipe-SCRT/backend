package school.sptech.backend.domain.cesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.backend.domain.cesta.Cesta;

import java.time.LocalDate;

public interface CestaRepository extends JpaRepository<Cesta, Integer> {

    @Query("SELECT coalesce(count(c), 0) FROM Cesta c WHERE MONTH(c.dataMontagem)= :mes AND YEAR(c.dataMontagem) = :ano")
    Integer qtdPorMesAno(@Param("mes") Integer mes, @Param("ano") Integer ano);


}
