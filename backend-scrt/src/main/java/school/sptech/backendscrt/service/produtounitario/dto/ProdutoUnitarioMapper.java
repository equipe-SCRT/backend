package school.sptech.backendscrt.service.produtounitario.dto;

import school.sptech.backendscrt.domain.produtounitario.ProdutoUnitario;

import java.util.List;

public class ProdutoUnitarioMapper {
    public static ProdutoUnitarioListagemDto toDto(ProdutoUnitario entity) {
        if (entity == null) return null;

        ProdutoUnitarioListagemDto dto = new ProdutoUnitarioListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDataValidade(entity.getDataValidade());
        dto.setPeso(entity.getPeso());
        dto.setFkOrigem(entity.getFkOrigem());
        dto.setFkUnidadeMedida(entity.getFkUnidadeMedida());
        dto.setFkCesta(entity.getFkCesta());
        dto.setFkProduto(entity.getFkProduto());
        dto.setFkRota(entity.getFkRota());

        return dto;
    }

    public static ProdutoUnitario toEntity(ProdutoUnitarioCriacaoDto dto) {
        if (dto == null) return null;

        ProdutoUnitario entity = new ProdutoUnitario();
        entity.setNome(dto.getNome());
        entity.setDataValidade(dto.getDataValidade());
        entity.setPeso(dto.getPeso());
        entity.setFkOrigem(dto.getFkOrigem());
        entity.setFkUnidadeMedida(dto.getFkUnidadeMedida());
        entity.setFkCesta(dto.getFkCesta());
        entity.setFkProduto(dto.getFkProduto());
        entity.setFkRota(dto.getFkRota());

        return entity;
    }

    public static List<ProdutoUnitarioListagemDto> toDto(List<ProdutoUnitario> entities) {

        return entities.stream().map(ProdutoUnitarioMapper::toDto).toList();
    }
}
