package school.sptech.backend.service.produtounitario.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoUnitarioMapper {

    ProdutoUnitarioListagemDto toDto(ProdutoUnitario entity);

    ProdutoUnitario toEntity(ProdutoUnitarioCriacaoDto dto);

    List<ProdutoUnitarioListagemDto> toDto(List<ProdutoUnitario> entities);

    ProdutoUnitario atualizacaoDto(ProdutoUnitarioAtualizacaoDto dto, Integer id);

}
