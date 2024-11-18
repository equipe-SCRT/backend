//package school.sptech.backend.domain.relatorio.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
//import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioCountMesDto;
//
//import java.util.List;
//
//public interface RelatorioRepository extends JpaRepository<ProdutoUnitario, Integer> {
//    @Query(
//            "SELECT p FROM ProdutoUnitario p WHERE p.dataValidade BETWEEN :dataInicial AND :dataFinal"
//    )
//    List<ProdutoUnitario> buscaPorPeriodo(String dataInicial, String dataFinal);
//
//    @Query("SELECT new school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioCountMesDto(" +
//            "FUNCTION('DATE_FORMAT', p.criadoEm, '%Y-%m'), COUNT(p)) " +
//            "FROM ProdutoUnitario p " +
//            "WHERE p.vencido = true " +
//            "GROUP BY FUNCTION('DATE_FORMAT', p.criadoEm, '%Y-%m') " +
//            "ORDER BY FUNCTION('DATE_FORMAT', p.criadoEm, '%Y-%m')")
//    List<ProdutoUnitarioCountMesDto> qtdVencidosPorMes();
//
//    @Query("SELECT new school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioCountMesDto(" +
//            "FUNCTION('DATE_FORMAT', p.criadoEm, '%Y-%m'), COUNT(p)) " +
//            "FROM ProdutoUnitario p " +
//            "WHERE p.ativo = true " +
//            "GROUP BY FUNCTION('DATE_FORMAT', p.criadoEm, '%Y-%m') " +
//            "ORDER BY FUNCTION('DATE_FORMAT', p.criadoEm, '%Y-%m')")
//    List<ProdutoUnitarioCountMesDto> qtdAtivosPorMes();
//}
