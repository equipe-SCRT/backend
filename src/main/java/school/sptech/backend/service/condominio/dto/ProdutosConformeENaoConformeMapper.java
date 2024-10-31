package school.sptech.backend.service.condominio.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.service.condominio.view.ProdutosConformeENaoConforme;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutosConformeENaoConformeMapper {

    ProdutosConformeENaoConformeListagemDto toDto(ProdutosConformeENaoConforme entity);
    List<ProdutosConformeENaoConformeListagemDto> toDto(List<ProdutosConformeENaoConforme> entities);
}
