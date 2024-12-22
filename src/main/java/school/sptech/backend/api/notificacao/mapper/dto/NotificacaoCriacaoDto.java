package school.sptech.backend.api.notificacao.mapper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacaoCriacaoDto {

    private String conteudo;
    private Integer tipoNotificacao;
}
