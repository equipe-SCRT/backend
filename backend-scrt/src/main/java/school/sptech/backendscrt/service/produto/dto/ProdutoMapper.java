package school.sptech.backendscrt.service.produto.dto;

import school.sptech.backendscrt.domain.produto.Produto;

import java.util.List;

public class ProdutoMapper {
    public static ProdutoListagemDto toDto(Produto entity) {
        if (entity == null) return null;

        ProdutoListagemDto dto = new ProdutoListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setFkTipoProduto(entity.getFkTipoProduto());

        return dto;
    }

    public static Produto toEntity(ProdutoCriacaoDto dto) {
        if (dto == null) return null;

        Produto entity = new Produto();
        entity.setNome(dto.getNome());
        entity.setFkTipoProduto(dto.getFkTipoProduto());

        return entity;
    }

    public static List<ProdutoListagemDto> toDto(List<Produto> entities) {

        return entities.stream().map(ProdutoMapper::toDto).toList();
    }
}
