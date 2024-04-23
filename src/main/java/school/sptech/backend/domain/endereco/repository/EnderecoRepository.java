package school.sptech.backend.domain.endereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.endereco.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
