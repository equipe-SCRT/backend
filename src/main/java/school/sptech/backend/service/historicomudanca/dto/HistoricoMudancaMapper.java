package school.sptech.backend.service.historicomudanca.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import school.sptech.backend.domain.historicomudanca.HistoricoMudanca;

@Mapper(componentModel = "String")
public interface HistoricoMudancaMapper{
    HistoricoMudancaMapper INSTANCE = Mappers.getMapper(HistoricoMudancaMapper.class);

    static HistoricoMudancaListagemDto toDto(HistoricoMudanca entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDto'");
    }
    static List<HistoricoMudancaListagemDto> toDto(List<HistoricoMudanca> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDto'");
    }
    static HistoricoMudanca toEntity(HistoricoMudancaCriacaoDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
    }
    static HistoricoMudanca toEntity(HistoricoMudancaAtualizacaoDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
    }
}
