package school.sptech.backend.domain.produtounitario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.service.produtounitario.view.VencidoArrecadado;

import java.util.List;

public interface VencidoArrecadadoRepository extends JpaRepository<VencidoArrecadado, Integer> {


}
