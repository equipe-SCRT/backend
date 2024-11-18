package school.sptech.backend.service.condominio.dto;

import org.mapstruct.Mapper;
import school.sptech.backend.service.condominio.view.ProdutosArrecadadosPorCondominio;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutosArrecadadosPorCondominioMapper {

    ProdutosArrecadadosPorCondominioListagemDto toDto(ProdutosArrecadadosPorCondominio entity);
    List<ProdutosArrecadadosPorCondominioListagemDto> toDto(List<ProdutosArrecadadosPorCondominio> entities);
}
