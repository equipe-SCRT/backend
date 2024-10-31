package school.sptech.backend.service.condominio.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.service.condominio.view.ProdutosArrecadadosPorMes;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutosArrecadadosPorMesMapper {

    ProdutosArrecadadosPorMesListagemDto toDto(ProdutosArrecadadosPorMes entity);
    List<ProdutosArrecadadosPorMesListagemDto>  toDto(List<ProdutosArrecadadosPorMes> entities);
}
