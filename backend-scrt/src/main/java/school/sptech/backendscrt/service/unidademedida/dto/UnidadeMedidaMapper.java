package school.sptech.backendscrt.service.unidademedida.dto;



import school.sptech.backendscrt.domain.unidademedida.UnidadeMedida;

import java.util.List;

public class UnidadeMedidaMapper {
    public static UnidadeMedidaListagemDto toDto(UnidadeMedida entity) {
        if (entity == null) return null;

        UnidadeMedidaListagemDto dto = new UnidadeMedidaListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());

        return dto;
    }

    public static UnidadeMedida toEntity(UnidadeMedidaCriacaoDto dto) {
        if (dto == null) return null;

        UnidadeMedida entity = new UnidadeMedida();
        entity.setNome(dto.getNome());
        entity.setRepresentacaao(dto.getRepresentacao());

        return entity;
    }

    public static UnidadeMedida atualizacaoDto(UnidadeMedida entity, UnidadeMedidaAtualizacao dto) {
        if (entity == null) return null;

        entity.setNome(dto.getNome());
        entity.setRepresentacaao(dto.getRepresentacao());

        return entity;
    }

    public static List<UnidadeMedidaListagemDto> toDto(List<UnidadeMedida> entities) {

        return entities.stream().map(UnidadeMedidaMapper::toDto).toList();
    }
}
