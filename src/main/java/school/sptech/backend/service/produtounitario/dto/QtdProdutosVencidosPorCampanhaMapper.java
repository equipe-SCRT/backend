package school.sptech.backend.service.produtounitario.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.service.produtounitario.view.QtdProdutosVencidosPorCampanha;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QtdProdutosVencidosPorCampanhaMapper {

    QtdProdutosVencidosPorCampanhaListagemDto toDto(QtdProdutosVencidosPorCampanha entity);

    List<QtdProdutosVencidosPorCampanhaListagemDto> toDto(List<QtdProdutosVencidosPorCampanha> entities);
}
