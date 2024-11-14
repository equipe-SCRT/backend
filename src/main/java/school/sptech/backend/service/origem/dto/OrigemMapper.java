package school.sptech.backend.service.origem.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.condominio.Condominio;
import school.sptech.backend.domain.origem.Origem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrigemMapper {

    OrigemListagemDto toDto(Origem entity);

    @Mapping(target = "campanha", source = "campanhaId", qualifiedByName = "campanhaFromId")
    @Mapping(target = "condominio", source = "condominioId", qualifiedByName = "condominioFromId")
    Origem toEntity(OrigemCriacaoDto dto);

    List<OrigemListagemDto> toDto(List<Origem> entities);

    Origem atualizacaoDto(OrigemAtualizacaoDto dto, int id);


    @Named("campanhaFromId")
    default Campanha campanhaFromId(Integer id) {
        if (id == null || id == 0) {
            return null;
        }
        Campanha campanha = new Campanha();
        campanha.setId(id);
        return campanha;
    }

    @Named("condominioFromId")
    default Condominio condominioFromId(Integer id) {
        if (id == null || id == 0) {
            return null;
        }
        Condominio condominio = new Condominio();
        condominio.setId(id);
        return condominio;
    }
}
