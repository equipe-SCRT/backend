package school.sptech.backend.service.tipocampanha.dto;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import school.sptech.backend.domain.tipocampanha.TipoCampanha;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoCampanhaMapper {

    TipoCampanhaListagemDto toDto(TipoCampanha entity);

    @InheritInverseConfiguration
    TipoCampanha toEntity(TipoCampanhaCriacaoDto dto);

    List<TipoCampanhaListagemDto> toDto(List<TipoCampanha> entities);

    TipoCampanha toEntity(TipoCampanhaAtualizacaoDto dto);
}
