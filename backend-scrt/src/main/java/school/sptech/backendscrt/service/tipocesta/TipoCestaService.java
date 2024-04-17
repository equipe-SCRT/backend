package school.sptech.backendscrt.service.tipocesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.backendscrt.domain.tipoCesta.TipoCesta;
import school.sptech.backendscrt.domain.tipoCesta.repository.TipoCestaRepository;
import school.sptech.backendscrt.service.tipocesta.dto.TipoCestaAtualizacaoDto;
import school.sptech.backendscrt.service.tipocesta.dto.TipoCestaCriacaoDto;
import school.sptech.backendscrt.service.tipocesta.dto.TipoCestaListagemDto;
import school.sptech.backendscrt.service.tipocesta.dto.TipoCestaMapper;

import java.util.List;
import java.util.Optional;

@Service
public class TipoCestaService {
    @Autowired
    private TipoCestaRepository tipoCestaRepository;

    public void criar(TipoCestaCriacaoDto tipoCestaCriacao){
        final TipoCesta novoTipoCesta = TipoCestaMapper.toEntity(tipoCestaCriacao);
        this.tipoCestaRepository.save(novoTipoCesta);
    }

    public List<TipoCestaListagemDto> listar(){
        final List<TipoCesta> tipoCestas = this.tipoCestaRepository.findAll();

        if (tipoCestas.isEmpty()){
            return null;
        }

        final List<TipoCestaListagemDto> dto = TipoCestaMapper.toDto(tipoCestas);

        return dto;
    }

    public TipoCestaListagemDto porId(int id){
        final Optional<TipoCesta> tipoCestaOpt = this.tipoCestaRepository.findById(id);

        if (tipoCestaOpt.isEmpty()){
            return null;
        }

        final TipoCestaListagemDto dto = TipoCestaMapper.toDto(tipoCestaOpt.get());

        return dto;
    }

    public TipoCestaListagemDto atualizar(TipoCestaAtualizacaoDto tipoCestaAtualizada, int id){
        final Optional<TipoCesta> tipoCestaOpt = this.tipoCestaRepository.findById(id);

        if (tipoCestaOpt.isEmpty()){
            return null;
        }

        final TipoCesta tipoCesta = TipoCestaMapper.atualizacaoDto(tipoCestaOpt.get(), tipoCestaAtualizada);

        final TipoCestaListagemDto dto = TipoCestaMapper.toDto(tipoCesta);

        this.tipoCestaRepository.save(tipoCesta);

        return dto;
    }

    public void deletar(int id){
        final Optional<TipoCesta> tipoCestaOpt = this.tipoCestaRepository.findById(id);

        if (tipoCestaOpt.isPresent()){
            this.tipoCestaRepository.deleteById(id);
        }
    }
}
