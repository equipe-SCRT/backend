package school.sptech.backend.service.metrica.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.domain.usuario.Usuario;

@Mapper(componentModel = "spring")
public interface MetricaMapper {

    MetricaListagemDto toDto(Metrica entity);
    List<MetricaListagemDto> toDto(List<Metrica> entities);

    @Mapping(target = "usuario", source = "fkUsuario", qualifiedByName = "usuarioFromId")
    Metrica toEntity(MetricaCriacaoDto dto);
    @Mapping(target = "usuario", source = "fkUsuario", qualifiedByName = "usuarioFromId")
    Metrica toEntity(MetricaAtualizacaoDto dto);

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
