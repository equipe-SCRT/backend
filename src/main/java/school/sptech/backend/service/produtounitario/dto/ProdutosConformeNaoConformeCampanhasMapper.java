package school.sptech.backend.service.produtounitario.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.service.produtounitario.view.ProdutosConformeNaoConformeCampanhas;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutosConformeNaoConformeCampanhasMapper {

    ProdutosConformeNaoConformeCampanhasListagemDto toDto(ProdutosConformeNaoConformeCampanhas entity);

    List<ProdutosConformeNaoConformeCampanhasListagemDto> toDto(List<ProdutosConformeNaoConformeCampanhas> entities);
}
