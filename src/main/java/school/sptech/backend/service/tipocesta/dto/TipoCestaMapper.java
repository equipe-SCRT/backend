package school.sptech.backend.service.tipocesta.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import school.sptech.backend.domain.tipocesta.TipoCesta;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoCestaMapper {
    TipoCestaMapper INSTANCE = Mappers.getMapper(TipoCestaMapper.class);
    
    TipoCestaListagemDto toDto(TipoCesta entity);
    List<TipoCestaListagemDto> toDto(List<TipoCesta> entities);
    TipoCesta toEntity(TipoCestaCriacaoDto dto);
    TipoCesta toEntity(TipoCestaAtualizacaoDto dto);
}
