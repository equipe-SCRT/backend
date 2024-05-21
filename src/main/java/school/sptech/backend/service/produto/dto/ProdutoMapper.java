package school.sptech.backend.service.produto.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import school.sptech.backend.domain.produto.Produto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    ProdutoListagemDto toDto(Produto entity);



    @InheritInverseConfiguration
    Produto toEntity(ProdutoCriacaoDto dto);

    List<ProdutoListagemDto> toDto(List<Produto> entities);

    Produto atualizacaoDto(ProdutoAtualizacaoDto dto, Integer id);
}
