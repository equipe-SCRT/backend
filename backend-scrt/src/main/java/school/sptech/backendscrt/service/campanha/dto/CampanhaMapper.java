package school.sptech.backendscrt.service.campanha.dto;

import school.sptech.backendscrt.domain.campanha.Campanha;

import java.util.List;

public class CampanhaMapper {
    public static CampanhaListagemDto toDto(Campanha entity) {
        if (entity == null) return null;

        CampanhaListagemDto dto = new CampanhaListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setLocal(entity.getLocal());
        dto.setDataCampanha(entity.getDataCampanha());
        dto.setQtdArrecadada(entity.getQtdArrecadada());
        dto.setMeta(entity.getMeta());

        return dto;
    }

    public static Campanha toEntity(CampanhaCriacaoDto dto) {
        if (dto == null) return null;

        Campanha entity = new Campanha();
        entity.setNome(dto.getNome());
        entity.setLocal(dto.getLocal());
        entity.setDataCampanha(dto.getDataCampanha());
        entity.setQtdArrecadada(dto.getQtdArrecadada());
        entity.setMeta(dto.getMeta());

        return entity;
    }

    public static List<CampanhaListagemDto> toDto(List<Campanha> entities) {

        return entities.stream().map(CampanhaMapper::toDto).toList();
    }
}
