package school.sptech.backend.service.endereco.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import school.sptech.backend.domain.endereco.Endereco;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    EnderecoListagemDto toDto(Endereco entity);
    List<EnderecoListagemDto> toDto(List<Endereco> entities);
    Endereco toEntity(EnderecoCriacaoDto dto);
    Endereco toEntity(EnderecoAtualizacaoDto dto);
}
