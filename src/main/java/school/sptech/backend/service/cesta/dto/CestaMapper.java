package school.sptech.backend.service.cesta.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.domain.tipocesta.TipoCesta;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CestaMapper {
    CestaMapper INSTANCE = Mappers.getMapper(CestaMapper.class);

    CestaListagemDto toDto(Cesta entity);
    List<CestaListagemDto> toDto(List<Cesta> entities);

    @Mapping(target = "tipoCesta", source = "tipoCestaId", qualifiedByName = "tipoCestaFromId")
    Cesta toEntity(CestaCriacaoDto dto);
    @Mapping(target = "tipoCesta", source = "tipoCestaId", qualifiedByName = "tipoCestaFromId")
    Cesta toEntity(CestaAtualizacaoDto dto);
    @Mapping(target = "tipoCesta", source = "tipoCestaId", qualifiedByName = "tipoCestaFromId")
    Cesta toEntity(CestaAtualizacaoTipoCestaIdDto dto);

    CestaCountAtivoDto toDto(Integer count);

    @Named("tipoCestaFromId")
    default TipoCesta tipoCestaFromId(Integer id) {
        if (id == null) {
            return null;
        }
        TipoCesta tipoCesta = new TipoCesta();
        tipoCesta.setId(id);
        return tipoCesta;
    }
}
