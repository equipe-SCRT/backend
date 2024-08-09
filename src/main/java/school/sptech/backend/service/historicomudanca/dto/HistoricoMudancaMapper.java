package school.sptech.backend.service.historicomudanca.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import school.sptech.backend.domain.historicomudanca.HistoricoMudanca;
import school.sptech.backend.domain.usuario.Usuario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoricoMudancaMapper{

    HistoricoMudancaListagemDto toDto(HistoricoMudanca entity);

    List<HistoricoMudancaListagemDto> toDto(List<HistoricoMudanca> entities);
    @Mapping(target = "usuario", source = "fkUsuario", qualifiedByName = "usuarioFromId")
    HistoricoMudanca toEntity(HistoricoMudancaCriacaoDto dto);
    @Mapping(target = "usuario", source = "fkUsuario", qualifiedByName = "usuarioFromId")
    HistoricoMudanca toEntity(HistoricoMudancaAtualizacaoDto dto);

    @Named("usuarioFromId")
    default Usuario usuarioFromId(Long id) {
        if (id == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(id);
        return usuario;
    }
}

