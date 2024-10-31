package school.sptech.backend.service.condominio.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.service.condominio.view.QtdProdutosNaoConforme;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QtdProdutosNaoConformeMapper {

    QtdProdutosNaoConformeListagemDto toDto(QtdProdutosNaoConforme entity);
    List<QtdProdutosNaoConformeListagemDto> toDto(List<QtdProdutosNaoConforme> entities);
}
