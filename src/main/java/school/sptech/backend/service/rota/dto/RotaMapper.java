package school.sptech.backend.service.rota.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import school.sptech.backend.domain.rota.Rota;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RotaMapper {

    RotaListagemDto toDto(Rota entity);

    Rota toEntity(RotaCriacaoDto dto);

    List<RotaListagemDto> toDto(List<Rota> entities);

    Rota toEntity(RotaAtualizacaoDto dto);
}
