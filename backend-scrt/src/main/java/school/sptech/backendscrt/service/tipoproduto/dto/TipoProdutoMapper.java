package school.sptech.backendscrt.service.tipoproduto.dto;

import school.sptech.backendscrt.domain.tipoProduto.TipoProduto;

import java.util.List;

public class TipoProdutoMapper {
    public static TipoProdutoListagemDto toDto(TipoProduto entity) {
        if (entity == null) return null;

        TipoProdutoListagemDto dto = new TipoProdutoListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());

        return dto;
    }

    public static TipoProduto toEntity(TipoProdutoCriacaoDto dto) {
        if (dto == null) return null;

        TipoProduto entity = new TipoProduto();
        entity.setNome(dto.getNome());

        return entity;
    }

    public static TipoProduto atualizacaoDto(TipoProduto entity, TipoProdutoAtualizacaoDto dto) {
        if (entity == null) return null;

        entity.setNome(dto.getNome());

        return entity;
    }

    public static List<TipoProdutoListagemDto> toDto(List<TipoProduto> entities) {

        return entities.stream().map(TipoProdutoMapper::toDto).toList();
    }
}
