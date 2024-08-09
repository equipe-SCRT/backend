package school.sptech.backend.service.unidademedida.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import school.sptech.backend.domain.unidademedida.UnidadeMedida;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadeMedidaMapper {

    UnidadeMedidaListagemDto toDto(UnidadeMedida entity);

    @InheritInverseConfiguration
    UnidadeMedida toEntity(UnidadeMedidaCriacaoDto dto);

    List<UnidadeMedidaListagemDto> toDto(List<UnidadeMedida> entities);

    UnidadeMedida toEntity(UnidadeMedidaAtualizacaoDto dto);
}
