package school.sptech.backendscrt.service.origem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.sptech.backendscrt.domain.origem.Origem;
import school.sptech.backendscrt.domain.origem.repository.OrigemRepository;
import school.sptech.backendscrt.service.origem.dto.OrigemAtualizacaoDto;
import school.sptech.backendscrt.service.origem.dto.OrigemCriacaoDto;
import school.sptech.backendscrt.service.origem.dto.OrigemListagemDto;
import school.sptech.backendscrt.service.origem.dto.OrigemMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrigemService {
    @Autowired
    private OrigemRepository origemRepository;

    public void criar(OrigemCriacaoDto origemCriacao){
        final Origem novaOrigem = OrigemMapper.toEntity(origemCriacao);
        this.origemRepository.save(novaOrigem);
    }

    public List<OrigemListagemDto> listar(){
        final List<Origem> origens = this.origemRepository.findAll();

        if (origens.isEmpty()){
            return null;
        }

        final List<OrigemListagemDto> dto = OrigemMapper.toDto(origens);

        return dto;
    }

    public OrigemListagemDto porId(int id){
        final Optional<Origem> origemOpt = this.origemRepository.findById(id);

        if (origemOpt.isEmpty()){
            return null;
        }

        final OrigemListagemDto dto = OrigemMapper.toDto(origemOpt.get());

        return dto;
    }

    public OrigemListagemDto atualizar(OrigemAtualizacaoDto origemAtualizada, int id){
        final Optional<Origem> origemOpt = this.origemRepository.findById(id);

        if (origemOpt.isEmpty()){
            return null;
        }

        final Origem origem = OrigemMapper.atualizacaoDto(origemOpt.get(), origemAtualizada);

        final OrigemListagemDto dto = OrigemMapper.toDto(origem);

        this.origemRepository.save(origem);

        return dto;
    }

    public void deletar(int id){
        final Optional<Origem> origemOpt = this.origemRepository.findById(id);

        if (origemOpt.isPresent()){
            this.origemRepository.deleteById(id);
        }
    }
}

