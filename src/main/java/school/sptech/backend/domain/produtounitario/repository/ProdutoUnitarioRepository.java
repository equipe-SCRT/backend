package school.sptech.backend.domain.produtounitario.repository;

import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioArrecadadoXVencidoDto;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioCountMesDto;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioVencimento15E30DiasDto;

import java.time.LocalDate;
import java.util.List;

public interface ProdutoUnitarioRepository extends JpaRepository<ProdutoUnitario, Integer> {
    List<ProdutoUnitario> findByNomeContainingIgnoreCase(String nome);

    Integer countByAtivoTrue();

    Integer countByDataValidadeBetween(LocalDate inicio, LocalDate fim);

    Integer countByAtivoTrueAndDataValidadeBefore(LocalDate data);

    @Query("SELECT new school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioCountMesDto(FUNCTION('DATE_FORMAT', p.dataCriacao, '%Y-%m'), COUNT(p)) " +
            "FROM ProdutoUnitario p " +
                "WHERE p.ativo = :ativo " +
                "GROUP BY FUNCTION('DATE_FORMAT', p.dataCriacao, '%Y-%m') " +
            "ORDER BY FUNCTION('DATE_FORMAT', p.dataCriacao, '%Y-%m')")
    List<ProdutoUnitarioCountMesDto> qtdAtivosPorMes(boolean ativo);

    @Query("SELECT new school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioVencimento15E30DiasDto(" +
            "SUM(CASE WHEN DATEDIFF(p.dataValidade, CURDATE()) <= 15 THEN 1 ELSE 0 END)," +
            "SUM(CASE WHEN DATEDIFF(p.dataValidade, CURDATE()) > 15 AND DATEDIFF(p.dataValidade, CURDATE()) <= 30  THEN 1 ELSE 0 END))" +
            " FROM ProdutoUnitario p"
    )
    ProdutoUnitarioVencimento15E30DiasDto alimentosVencimento15E30Dias();

    @Query("SELECT new school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioArrecadadoXVencidoDto(p.nome, " +
            "(SELECT COUNT(p1) FROM ProdutoUnitario p1 WHERE p1.ativo = true AND p1.nome = p.nome), " +
            "(SELECT COUNT(p2) FROM ProdutoUnitario p2 WHERE p2.ativo = false AND p2.nome = p.nome)) " +
            "FROM ProdutoUnitario p GROUP BY p.nome")
    List<ProdutoUnitarioArrecadadoXVencidoDto> countAtivoByNome();

}
