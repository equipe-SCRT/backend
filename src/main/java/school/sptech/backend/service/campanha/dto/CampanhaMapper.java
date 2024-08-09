package school.sptech.backend.service.campanha.dto;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.backend.service.produto.dto.ProdutoListagemDto;
import school.sptech.backend.service.produto.dto.ProdutoMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CampanhaMapper {

    CampanhaListagemDto toDto(Campanha entity);

    @InheritInverseConfiguration
    Campanha toEntity(CampanhaCriacaoDto dto);

    List<CampanhaListagemDto> toDto(List<Campanha> entities);

    Campanha toEntity(CampanhaAtualizacaoDto dto);

}
