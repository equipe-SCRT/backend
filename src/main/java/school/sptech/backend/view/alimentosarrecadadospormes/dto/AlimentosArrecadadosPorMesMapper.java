package school.sptech.backend.view.alimentosarrecadadospormes.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.view.alimentosarrecadadospormes.AlimentosArrecadadosPorMes;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlimentosArrecadadosPorMesMapper {

    AlimentosArrecadadosPorMesListagemDto toDto(AlimentosArrecadadosPorMes entity);

    List<AlimentosArrecadadosPorMesListagemDto> toDto(List<AlimentosArrecadadosPorMes> entities);

}
