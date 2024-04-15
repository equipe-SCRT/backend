package school.sptech.backendscrt.service.historicomudanca.dto;

import school.sptech.backendscrt.domain.historicomudanca.HistoricoMudanca;

import java.util.List;

public class HistoricoMudancaMapper {
    public static HistoricoMudancaListagemDto toDto(HistoricoMudanca entity) {
        if (entity == null) return null;

        HistoricoMudancaListagemDto dto = new HistoricoMudancaListagemDto();
        dto.setId(entity.getId());
        dto.setDataHora(entity.getDataHora());
        dto.setFkUsuario(entity.getFkUsuario());

        return dto;
    }

    public static HistoricoMudanca toEntity(HistoricoMudancaCriacaoDto dto) {
        if (dto == null) return null;

        HistoricoMudanca entity = new HistoricoMudanca();
        entity.setDataHora(dto.getDataHora());
        entity.setFkUsuario(dto.getFkUsuario());

        return entity;
    }

    public static List<HistoricoMudancaListagemDto> toDto(List<HistoricoMudanca> entities) {

        return entities.stream().map(HistoricoMudancaMapper::toDto).toList();
    }
}
