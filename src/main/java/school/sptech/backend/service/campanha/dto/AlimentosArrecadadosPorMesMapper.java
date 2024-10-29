package school.sptech.backend.service.campanha.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.service.campanha.view.AlimentosArrecadadosPorMes;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlimentosArrecadadosPorMesMapper {

    AlimentosArrecadadosPorMesListagemDto toDto(AlimentosArrecadadosPorMes entity);

    List<AlimentosArrecadadosPorMesListagemDto> toDto(List<AlimentosArrecadadosPorMes> entities);

}
