package school.sptech.backend.service.cesta.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import school.sptech.backend.domain.cesta.Cesta;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CestaMapper {
    CestaMapper INSTANCE = Mappers.getMapper(CestaMapper.class);

    CestaListagemDto cestaToDto(Cesta cesta);
    List<CestaListagemDto> cestaToDto(List<Cesta> cestas);
    Cesta dtoToCesta(CestaCriacaoDto dto);
}
