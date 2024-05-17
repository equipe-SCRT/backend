package school.sptech.backend.service.campanha.dto;

import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.backend.service.produto.dto.ProdutoListagemDto;
import school.sptech.backend.service.produto.dto.ProdutoMapper;

import java.util.List;

public class CampanhaMapper {

    public static CampanhaListagemDto toDto(Campanha entity) {
        if (entity == null) return null;

        CampanhaListagemDto dto = new CampanhaListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setLocal_campanha(entity.getLocal_campanha());
        dto.setMeta(entity.getMeta());
        dto.setQtd_arrecadada(entity.getQtd_arrecadada());
        dto.setData_campanha(entity.getData_campanha());

        return dto;
    }

    public static Campanha toEntity(CampanhaCriacaoDto dto){
        if(dto == null) return null;

        Campanha entity = new Campanha();
        entity.setNome(dto.getNome());
        entity.setLocal_campanha(dto.getLocal_campanha());
        entity.setQtd_arrecadada(dto.getQtd_arrecadada());
        entity.setMeta(dto.getMeta());
        entity.setData_campanha(dto.getData_campanha());

        return entity;

    }

    public static List<CampanhaListagemDto> toDto(List<Campanha> entities) {

        return entities.stream().map(CampanhaMapper::toDto).toList();
    }

    public static Campanha atualizacaoDto(Campanha entity, CampanhaAtualizacaoDto dto) {
        if (entity == null) return null;

        entity.setNome(dto.getNome());
        entity.setNome(dto.getNome());
        entity.setLocal_campanha(dto.getLocal_campanha());
        entity.setQtd_arrecadada(dto.getQtd_arrecadada());
        entity.setMeta(dto.getMeta());
        entity.setData_campanha(dto.getData_campanha());

        return entity;
    }
}
