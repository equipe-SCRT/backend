package school.sptech.backend.service.origem.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.origem.Origem;
import school.sptech.backend.service.campanha.dto.CampanhaAtualizacaoDto;
import school.sptech.backend.service.campanha.dto.CampanhaCriacaoDto;
import school.sptech.backend.service.campanha.dto.CampanhaListagemDto;
import school.sptech.backend.service.campanha.dto.CampanhaMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrigemMapper {

    OrigemListagemDto toDto(Origem entity);

    @InheritInverseConfiguration
    Origem toEntity(OrigemCriacaoDto dto);

    List<OrigemListagemDto> toDto(List<Origem> entities);

    Origem atualizacaoDto(OrigemAtualizacaoDto dto, int id);
}
