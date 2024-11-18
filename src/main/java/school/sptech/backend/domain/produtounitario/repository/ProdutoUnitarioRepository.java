package school.sptech.backend.domain.produtounitario.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioCountDto;
import school.sptech.backend.service.produtounitario.dto.QtdProdutoPorCampanhaListagemDto;

import java.time.LocalDate;
import java.util.List;

public interface ProdutoUnitarioRepository extends JpaRepository<ProdutoUnitario, Integer> {
    List<ProdutoUnitario> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT COUNT(*) FROM ProdutoUnitario p")
    ProdutoUnitarioCountDto countVencidosMesAtual();

    @Modifying
    @Transactional
    @Query(
            "UPDATE ProdutoUnitario p SET p.vencido = true WHERE p.ativo = true AND DATE(p.dataValidade) < DATE(now()) "
    )
    void verificarProdutosForaDaValidade();

    List<ProdutoUnitario> findByDataValidadeBetween(LocalDate inicio, LocalDate fim);

    List<ProdutoUnitario> findByDataValidadeBefore(LocalDate data);

}
