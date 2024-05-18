package school.sptech.backend.service.condominio.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import school.sptech.backend.domain.condominio.Condominio;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CondominioMapper {

    CondominioListagemDto toDto(Condominio entity);

    @InheritInverseConfiguration
    Condominio toEntity(CondominioCriacaoDto dto);

    List<CondominioListagemDto> toDto(List<Condominio> entities);

    Condominio atualizacaoDto(CondominioAtualizacaoDto dto, @MappingTarget Condominio entity);
}
