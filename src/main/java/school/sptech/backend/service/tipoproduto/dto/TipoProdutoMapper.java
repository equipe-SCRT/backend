package school.sptech.backend.service.tipoproduto.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import school.sptech.backend.domain.tipoproduto.TipoProduto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoProdutoMapper {
    TipoProdutoListagemDto toDto(TipoProduto entity);

    @InheritInverseConfiguration
    TipoProduto toEntity(TipoProdutoCriacaoDto dto);

    List<TipoProdutoListagemDto> toDto(List<TipoProduto> entities);

    TipoProduto toEntity(TipoProdutoAtualizacaoDto dto);
}
