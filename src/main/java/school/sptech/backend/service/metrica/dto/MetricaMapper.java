package school.sptech.backend.service.metrica.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.domain.usuario.Usuario;

@Mapper(componentModel = "spring")
public interface MetricaMapper {

    MetricaListagemDto toDto(Metrica entity);
    List<MetricaListagemDto> toDto(List<Metrica> entities);

    Metrica toEntity(MetricaCriacaoDto dto);
    Metrica toEntity(MetricaAtualizacaoDto dto);


}
