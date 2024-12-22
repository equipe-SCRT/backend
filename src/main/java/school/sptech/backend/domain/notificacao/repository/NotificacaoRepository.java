package school.sptech.backend.domain.notificacao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.notificacao.Notificacao;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Integer> {
}
