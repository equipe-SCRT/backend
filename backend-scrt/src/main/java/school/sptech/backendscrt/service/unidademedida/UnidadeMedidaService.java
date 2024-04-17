package school.sptech.backendscrt.service.unidademedida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.backendscrt.domain.unidademedida.UnidadeMedida;
import school.sptech.backendscrt.domain.unidademedida.repository.UnidadeMedidaRepository;
import school.sptech.backendscrt.service.unidademedida.dto.UnidadeMedidaAtualizacao;
import school.sptech.backendscrt.service.unidademedida.dto.UnidadeMedidaCriacaoDto;
import school.sptech.backendscrt.service.unidademedida.dto.UnidadeMedidaListagemDto;
import school.sptech.backendscrt.service.unidademedida.dto.UnidadeMedidaMapper;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeMedidaService {
    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    public void criar(UnidadeMedidaCriacaoDto unidadeMedidaCriacao){
        final UnidadeMedida novaUnidadeMedida = UnidadeMedidaMapper.toEntity(unidadeMedidaCriacao);
        this.unidadeMedidaRepository.save(novaUnidadeMedida);
    }

    public List<UnidadeMedidaListagemDto> listar(){
        final List<UnidadeMedida> unidadeMedidas = this.unidadeMedidaRepository.findAll();

        if (unidadeMedidas.isEmpty()){
            return null;
        }

        final List<UnidadeMedidaListagemDto> dto = UnidadeMedidaMapper.toDto(unidadeMedidas);

        return dto;
    }

    public UnidadeMedidaListagemDto porId(int id){
        final Optional<UnidadeMedida> unidadeMedidaOpt = this.unidadeMedidaRepository.findById(id);

        if (unidadeMedidaOpt.isEmpty()){
            return null;
        }

        final UnidadeMedidaListagemDto dto = UnidadeMedidaMapper.toDto(unidadeMedidaOpt.get());

        return dto;
    }

    public UnidadeMedidaListagemDto atualizar(UnidadeMedidaAtualizacao tipoCestaAtualizada, int id){
        final Optional<UnidadeMedida> unidadeMedidaOpt = this.unidadeMedidaRepository.findById(id);

        if (unidadeMedidaOpt.isEmpty()){
            return null;
        }

        final UnidadeMedida unidadeMedidas = UnidadeMedidaMapper.atualizacaoDto(unidadeMedidaOpt.get(), tipoCestaAtualizada);

        final UnidadeMedidaListagemDto dto = UnidadeMedidaMapper.toDto(unidadeMedidas);

        this.unidadeMedidaRepository.save(unidadeMedidas);

        return dto;
    }

    public void deletar(int id){
        final Optional<UnidadeMedida> unidadeMedidaOpt = this.unidadeMedidaRepository.findById(id);

        if (unidadeMedidaOpt.isPresent()){
            this.unidadeMedidaRepository.deleteById(id);
        }
    }
}
