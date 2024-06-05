package school.sptech.backend.domain.produtounitario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;

import java.util.List;

public interface ProdutoUnitarioRepository extends JpaRepository<ProdutoUnitario, Integer> {
    List<ProdutoUnitario> findByNomeContainingIgnoreCase(String nome);
}
