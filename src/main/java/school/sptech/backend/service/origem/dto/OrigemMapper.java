package school.sptech.backend.service.origem.dto;

import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.origem.Origem;
import school.sptech.backend.service.campanha.dto.CampanhaAtualizacaoDto;
import school.sptech.backend.service.campanha.dto.CampanhaCriacaoDto;
import school.sptech.backend.service.campanha.dto.CampanhaListagemDto;
import school.sptech.backend.service.campanha.dto.CampanhaMapper;

import java.util.List;

public class OrigemMapper {

    public static OrigemListagemDto toDto(Origem entity) {
        if (entity == null) return null;

        OrigemListagemDto dto = new OrigemListagemDto();
        dto.setId(entity.getId());
        dto.setAuta_de_souza_rua(entity.getAuta_de_souza_rua());
        dto.setItapora(entity.getItapora());
        dto.setFkCampanha(entity.getFkCampanha());
        dto.setFkCondominio(entity.getFkCondominio());

        return dto;
    }

    public static Origem toEntity(OrigemCriacaoDto dto){
        if(dto == null) return null;

        Origem entity = new Origem();
        entity.setAuta_de_souza_rua(dto.getAuta_de_souza_rua());
        entity.setItapora(dto.getItapora());
        entity.setFkCampanha(dto.getFkCampanha());
        entity.setFkCondominio(dto.getFkCondominio());

        return entity;

    }

    public static List<OrigemListagemDto> toDto(List<Origem> entities) {

        return entities.stream().map(OrigemMapper::toDto).toList();
    }

    public static Origem atualizacaoDto(Origem entity, OrigemAtualizacaoDto dto) {
        if (entity == null) return null;

        entity.setFkCondominio(dto.getFkCondominio());
        entity.setItapora(dto.getItapora());
        entity.setFkCondominio(dto.getFkCondominio());
        entity.setFkCampanha(dto.getFkCampanha());
        entity.setAuta_de_souza_rua(dto.getAuta_de_souza_rua());

        return entity;
    }
}
