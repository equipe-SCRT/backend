package school.sptech.backend.service.produto.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.tipoproduto.TipoProduto;
import school.sptech.backend.domain.unidademedida.UnidadeMedida;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    ProdutoListagemDto toDto(Produto entity);

    @Mapping(target = "tipoProduto", source = "tipoProdutoId", qualifiedByName = "tipoProdutoFromId")
    @Mapping(target = "unidadeMedida", source = "tipoUnidadeMedidaId", qualifiedByName = "tipoUnidadeMedidaFromId")
    Produto toEntity(ProdutoCriacaoDto dto);

    List<ProdutoListagemDto> toDto(List<Produto> entities);

    @Mapping(target = "tipoProduto", source = "tipoProdutoId", qualifiedByName = "tipoProdutoFromId")
    @Mapping(target = "unidadeMedida", source = "tipoUnidadeMedidaId", qualifiedByName = "tipoUnidadeMedidaFromId")
    Produto atualizacaoDto(ProdutoAtualizacaoDto dto);

    @Named("tipoProdutoFromId")
    default TipoProduto tipoProdutoFromId(Integer id) {
        if (id == null) {
            return null;
        }
        TipoProduto tipoProduto = new TipoProduto();
        tipoProduto.setId(id);
        return tipoProduto;
    }

    @Named("tipoUnidadeMedidaFromId")
    default UnidadeMedida tipoUnidadeMedidaFromId(Integer id) {
        if (id == null) {
            return null;
        }
        UnidadeMedida tipoUnidadeMedida = new UnidadeMedida();
        tipoUnidadeMedida.setId(id);
        return tipoUnidadeMedida;
    }
}
