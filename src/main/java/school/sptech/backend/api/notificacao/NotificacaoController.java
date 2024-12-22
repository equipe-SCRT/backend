package school.sptech.backend.api.notificacao;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.backend.api.notificacao.mapper.NotificacaoMapper;
import school.sptech.backend.api.notificacao.mapper.dto.NotificacaoCriacaoDto;
import school.sptech.backend.api.notificacao.mapper.dto.NotificacaoListagemDto;
import school.sptech.backend.domain.notificacao.Notificacao;
import school.sptech.backend.service.notificacao.NotificacaoService;

@RestController
@RequestMapping("/notificacoes")
@RequiredArgsConstructor
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    @PostMapping
    public ResponseEntity<NotificacaoListagemDto> criarNotificacao(@RequestBody NotificacaoCriacaoDto criacaoDto){
        Notificacao notificacao = NotificacaoMapper.toEntity(criacaoDto);
        NotificacaoListagemDto dto = NotificacaoMapper.toListagemDto(notificacaoService.criarNotificacao(notificacao));

        return ResponseEntity.status(201).body(dto);
    }
}
