package school.sptech.backend.service.condominio.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.service.condominio.view.QtdProdutosVencidos;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QtdProdutosVencidosMapper {

    QtdProdutosVencidosListagemDto toDto(QtdProdutosVencidos entity);
    List<QtdProdutosVencidosListagemDto> toDto(List<QtdProdutosVencidos> entities);
}
