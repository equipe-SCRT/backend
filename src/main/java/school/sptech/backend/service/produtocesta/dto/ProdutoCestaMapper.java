package school.sptech.backend.service.produtocesta.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.domain.produtocesta.ProdutoCesta;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoCestaMapper {

    ProdutoCesta toEntity(ProdutoCestaCriacaoDto produtoCestaCriacaoDto);

    List<ProdutoCestaEntityDto> toDto(List<ProdutoCesta> produtoCestas);

    ProdutoCestaEntityDto toDto(ProdutoCesta produtoCesta);
}