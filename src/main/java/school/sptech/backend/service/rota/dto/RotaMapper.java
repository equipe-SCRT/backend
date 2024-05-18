package school.sptech.backend.service.rota.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import school.sptech.backend.domain.rota.Rota;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RotaMapper {

    RotaListagemDto toDto(Rota entity);

    @InheritInverseConfiguration
    Rota toEntity(RotaCriacaoDto dto);

    List<RotaListagemDto> toDto(List<Rota> entities);

    Rota atualizacaoDto(RotaAtualizacaoDto dto, @MappingTarget Rota entity);
}
