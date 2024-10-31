package school.sptech.backend.service.condominio.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.service.condominio.view.QtdProdutosPorNomeCondominio;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QtdProdutosPorNomeCondominioMapper {

    QtdProdutosPorNomeCondominioListagemDto toDto(QtdProdutosPorNomeCondominio entity);
    List<QtdProdutosPorNomeCondominioListagemDto> toDto(List<QtdProdutosPorNomeCondominio> entities);
}
