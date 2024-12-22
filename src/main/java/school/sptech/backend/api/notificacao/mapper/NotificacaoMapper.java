package school.sptech.backend.api.notificacao.mapper;

import lombok.RequiredArgsConstructor;
import school.sptech.backend.api.notificacao.NotificacaoController;
import school.sptech.backend.api.notificacao.mapper.dto.NotificacaoCriacaoDto;
import school.sptech.backend.api.notificacao.mapper.dto.NotificacaoListagemDto;
import school.sptech.backend.domain.notificacao.Notificacao;

@RequiredArgsConstructor
public class NotificacaoMapper {

    public static Notificacao toEntity(NotificacaoCriacaoDto dto){
        Notificacao notificacao = new Notificacao();

        notificacao.setTipoNotificacao(dto.getTipoNotificacao());
        notificacao.setConteudo(dto.getConteudo());

        return notificacao;
    }

    public static NotificacaoListagemDto toListagemDto(Notificacao entity){
        NotificacaoListagemDto dto = new NotificacaoListagemDto();

        dto.setConteudo(entity.getConteudo());
        dto.setTipoNotificacao(entity.getTipoNotificacao());

        return dto;
    }
}
