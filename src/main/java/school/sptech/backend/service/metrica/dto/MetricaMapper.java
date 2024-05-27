package school.sptech.backend.service.metrica.dto;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import school.sptech.backend.domain.metrica.Metrica;

@Mapper(componentModel = "spring")
public interface MetricaMapper {
  //  MetricaMapper INSTANCE = Mappers.getMapper(MetricaMapper.class);


    MetricaListagemDto toDto(Metrica entity);
  
    List<MetricaListagemDto> toDto(List<Metrica> entities);

    @InheritInverseConfiguration
    Metrica toEntity(MetricaCriacaoDto dto);

    Metrica toEntity(MetricaAtualizacaoDto dto, Integer id);

}
