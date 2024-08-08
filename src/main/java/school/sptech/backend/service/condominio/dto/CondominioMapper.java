package school.sptech.backend.service.condominio.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import school.sptech.backend.domain.condominio.Condominio;
import school.sptech.backend.domain.endereco.Endereco;
import school.sptech.backend.domain.usuario.Usuario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CondominioMapper {

    CondominioListagemDto toDto(Condominio entity);

    @Mapping(target = "endereco", source = "enderecoId", qualifiedByName = "enderecoFromId")
    Condominio toEntity(CondominioCriacaoDto dto);

    List<CondominioListagemDto> toDto(List<Condominio> entities);

    @Mapping(target = "endereco", source = "enderecoId", qualifiedByName = "enderecoFromId")
    Condominio toEntity(CondominioAtualizacaoDto dto);

    @Named("enderecoFromId")
    default Endereco enderecoFromId(Integer id) {
        if (id == null) {
            return null;
        }
        Endereco endereco = new Endereco();
        endereco.setId(id);
        return endereco;
    }
}
