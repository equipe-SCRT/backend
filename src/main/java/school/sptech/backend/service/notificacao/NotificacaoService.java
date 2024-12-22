package school.sptech.backend.service.notificacao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.notificacao.Notificacao;
import school.sptech.backend.domain.notificacao.repository.NotificacaoRepository;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    public Notificacao criarNotificacao(Notificacao notificacao){
        return notificacaoRepository.save(notificacao);
    }
}
