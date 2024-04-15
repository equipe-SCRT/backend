package school.sptech.backendscrt.service.historicomudanca;

import org.springframework.beans.factory.annotation.Autowired;
import school.sptech.backendscrt.domain.historicomudanca.HistoricoMudanca;
import school.sptech.backendscrt.domain.historicomudanca.repository.HistoricoMudancaRepository;
import school.sptech.backendscrt.service.historicomudanca.dto.HistoricoMudancaAtualizacaoDto;
import school.sptech.backendscrt.service.historicomudanca.dto.HistoricoMudancaCriacaoDto;
import school.sptech.backendscrt.service.historicomudanca.dto.HistoricoMudancaListagemDto;
import school.sptech.backendscrt.service.historicomudanca.dto.HistoricoMudancaMapper;

import java.util.List;
import java.util.Optional;

public class HistoricoMudancaService {
    @Autowired
    private HistoricoMudancaRepository historicoMudancaRepository;

    public List<HistoricoMudancaListagemDto> listar() {
        final List<HistoricoMudanca> historicosMudanca = this.historicoMudancaRepository.findAll();
        return HistoricoMudancaMapper.toDto(historicosMudanca);
    }
    
    public void adicionar(HistoricoMudancaCriacaoDto historicoMudancaCriacaoDto) {
        final HistoricoMudanca novoHistoricoMudanca = HistoricoMudancaMapper.toEntity(historicoMudancaCriacaoDto);
        this.historicoMudancaRepository.save(novoHistoricoMudanca);
    }

    public void atualizar(int id, HistoricoMudancaAtualizacaoDto historicoMudancaAtualizacaoDto) {
        Optional<HistoricoMudanca> historicoMudancaOpt = historicoMudancaRepository.findById(id);

        historicoMudancaOpt.get().setId(id);
        historicoMudancaOpt.get().setDataHora(historicoMudancaAtualizacaoDto.getDataHora());
        historicoMudancaOpt.get().setFkUsuario(historicoMudancaAtualizacaoDto.getFkUsuario());

        HistoricoMudanca historicoMudancaSalvo = historicoMudancaRepository.save(historicoMudancaOpt.get());
        HistoricoMudancaMapper.toDto(historicoMudancaSalvo);
    }

    public void deletar(int id) {
        Optional<HistoricoMudanca> HistoricoMudancaOpt = this.historicoMudancaRepository.findById(id);
        this.historicoMudancaRepository.delete(HistoricoMudancaOpt.get());
    }
}
