package school.sptech.backend.service.cesta.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import school.sptech.backend.domain.cesta.Cesta;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CestaMapper {
    CestaMapper INSTANCE = Mappers.getMapper(CestaMapper.class);

    CestaListagemDto toDto(Cesta entity);
    List<CestaListagemDto> toDto(List<Cesta> entities);
    Cesta toEntity(CestaCriacaoDto dto);
    Cesta toEntity(CestaAtualizacaoDto dto);
    Cesta toEntity(CestaAtualizacaoTipoCestaIdDto dto);
}
