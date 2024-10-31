package school.sptech.backend.service.produtounitario.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.service.produtounitario.view.QtdProdutoPorCampanha;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QtdProdutoPorCampanhaMapper {

    QtdProdutoPorCampanhaListagemDto toDto(QtdProdutoPorCampanha entity);

    List<QtdProdutoPorCampanhaListagemDto> toDto(List<QtdProdutoPorCampanha> entities);
}
