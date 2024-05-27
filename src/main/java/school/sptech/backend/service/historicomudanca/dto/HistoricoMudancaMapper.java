package school.sptech.backend.service.historicomudanca.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;
import school.sptech.backend.domain.historicomudanca.HistoricoMudanca;

import java.util.List;



    @Mapper(componentModel = "spring")
    public interface HistoricoMudancaMapper{
       // school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaMapper INSTANCE = Mappers.getMapper(school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaMapper.class);

        HistoricoMudancaListagemDto toDto(HistoricoMudanca entity);

        List<HistoricoMudancaListagemDto> toDto(List<HistoricoMudanca> entities);
        @InheritInverseConfiguration
        HistoricoMudanca toEntity(HistoricoMudancaCriacaoDto dto);

        HistoricoMudanca toEntity(HistoricoMudancaAtualizacaoDto dto);
    }
