package school.sptech.backendscrt.service.origem.dto;

import school.sptech.backendscrt.domain.origem.Origem;

import java.util.List;

public class OrigemMapper {
    public static OrigemListagemDto toDto(Origem entity) {
        if (entity == null) return null;

        OrigemListagemDto dto = new OrigemListagemDto();
        dto.setId(entity.getId());
        dto.setAutaDeSousaRua(entity.getAutaDeSousaRua());
        dto.setItapora(entity.getItapora());
        dto.setFkCondominio(entity.getFkCondominio());
        dto.setFkCampanha(entity.getFkCampanha());

        return dto;
    }

    public static Origem toEntity(OrigemCriacaoDto dto) {
        if (dto == null) return null;

        Origem entity = new Origem();
        entity.setAutaDeSousaRua(dto.getAutaDeSousaRua());
        entity.setItapora(dto.getItapora());
        entity.setFkCondominio(dto.getFkCondominio());
        entity.setFkCampanha(dto.getFkCampanha());

        return entity;
    }

    public static Origem atualizacaoDto(Origem entity, OrigemAtualizacaoDto dto) {
        if (entity == null) return null;

        entity.setAutaDeSousaRua(dto.getAutaDeSousaRua());
        entity.setItapora(dto.getItapora());
        entity.setFkCondominio(dto.getFkCondominio());
        entity.setFkCampanha(dto.getFkCampanha());

        return entity;
    }


    public static List<OrigemListagemDto> toDto(List<Origem> entities) {

        return entities.stream().map(OrigemMapper::toDto).toList();
    }
}
