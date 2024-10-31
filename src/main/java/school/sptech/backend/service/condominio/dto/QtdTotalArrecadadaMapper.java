package school.sptech.backend.service.condominio.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.service.condominio.view.QtdTotalArrecadada;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QtdTotalArrecadadaMapper {

    QtdTotalArrecadadaListagemDto toDto(QtdTotalArrecadada entity);
    List<QtdTotalArrecadadaListagemDto> toDto(List<QtdTotalArrecadada> entities);
}
