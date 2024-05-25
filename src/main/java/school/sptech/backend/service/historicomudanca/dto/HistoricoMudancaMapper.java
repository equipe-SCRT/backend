package school.sptech.backend.service.historicomudanca.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import school.sptech.backend.domain.historicomudanca.HistoricoMudanca;

@Mapper(componentModel = "spring")
public interface HistoricoMudancaMapper{
    HistoricoMudancaMapper INSTANCE = Mappers.getMapper(HistoricoMudancaMapper.class);

     HistoricoMudancaListagemDto toDto(HistoricoMudanca entity);
     List<HistoricoMudancaListagemDto> toDto(List<HistoricoMudanca> entities);
     HistoricoMudanca toEntity(HistoricoMudancaCriacaoDto dto);
     HistoricoMudanca toEntity(HistoricoMudancaAtualizacaoDto dto);
}
