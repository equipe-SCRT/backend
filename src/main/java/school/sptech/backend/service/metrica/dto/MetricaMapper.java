package school.sptech.backend.service.metrica.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaMapper;

@Mapper(componentModel = "String")
public interface MetricaMapper {
    MetricaMapper INSTANCE = Mappers.getMapper(MetricaMapper.class);

    MetricaListagemDto toDto(Metrica entity);
    static List<MetricaListagemDto> toDto(List<Metrica> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDto'");
    }
    static Metrica toEntity(MetricaCriacaoDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
    }
    Metrica toEntity(MetricaAtualizacaoDto dto);
}
