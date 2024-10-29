package school.sptech.backend.service.campanha.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.service.campanha.view.QtdDoacoesPorCampanha;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QtdDoacoesPorCampanhaMapper {

    QtdDoacoesPorCampanhaListagemDto toDto(QtdDoacoesPorCampanha entity);

    List<QtdDoacoesPorCampanhaListagemDto> toDto(List<QtdDoacoesPorCampanha> entities);

}
