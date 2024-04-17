package school.sptech.backendscrt.service.tipocesta.dto;

import school.sptech.backendscrt.domain.tipoCesta.TipoCesta;

import java.util.List;

public class TipoCestaMapper {
    public static TipoCestaListagemDto toDto(TipoCesta entity) {
        if (entity == null) return null;

        TipoCestaListagemDto dto = new TipoCestaListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());

        return dto;
    }

    public static TipoCesta toEntity(TipoCestaCriacaoDto dto) {
        if (dto == null) return null;

        TipoCesta entity = new TipoCesta();
        entity.setNome(dto.getNome());

        return entity;
    }

    public static TipoCesta atualizacaoDto(TipoCesta entity, TipoCestaAtualizacaoDto dto) {
        if (entity == null) return null;

        entity.setNome(dto.getNome());

        return entity;
    }

    public static List<TipoCestaListagemDto> toDto(List<TipoCesta> entities) {

        return entities.stream().map(TipoCestaMapper::toDto).toList();
    }
}
