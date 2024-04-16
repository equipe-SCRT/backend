package school.sptech.backendscrt.service.campanha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.backendscrt.domain.campanha.Campanha;
import school.sptech.backendscrt.domain.campanha.repository.CampanhaRepository;
import school.sptech.backendscrt.service.campanha.dto.CampanhaAtualizacaoDto;
import school.sptech.backendscrt.service.campanha.dto.CampanhaCriacaoDto;
import school.sptech.backendscrt.service.campanha.dto.CampanhaListagemDto;
import school.sptech.backendscrt.service.campanha.dto.CampanhaMapper;

import java.util.List;
import java.util.Optional;

@Service
public class CampanhaService {
    @Autowired
    private CampanhaRepository campanhaRepository;

    public List<CampanhaListagemDto> listar() {
        final List<Campanha> campanhas = this.campanhaRepository.findAll();
        return CampanhaMapper.toDto(campanhas);
    }

    public void adicionar(CampanhaCriacaoDto campanhaCriacaoDto) {
        final Campanha novaCampanha = CampanhaMapper.toEntity(campanhaCriacaoDto);
        this.campanhaRepository.save(novaCampanha);
    }

    public void atualizar(int id, CampanhaAtualizacaoDto campanhaAtualizacaoDto) {
        Optional<Campanha> campanhaOpt = campanhaRepository.findById(id);

        campanhaOpt.get().setId(id);
        campanhaOpt.get().setNome(campanhaAtualizacaoDto.getNome());
        campanhaOpt.get().setLocal(campanhaAtualizacaoDto.getLocal());
        campanhaOpt.get().setDataCampanha(campanhaAtualizacaoDto.getDataCampanha());
        campanhaOpt.get().setQtdArrecadada(campanhaAtualizacaoDto.getQtdArrecadada());
        campanhaOpt.get().setMeta(campanhaAtualizacaoDto.getMeta());

        Campanha campanhaSalva = campanhaRepository.save(campanhaOpt.get());
        CampanhaMapper.toDto(campanhaSalva);
    }

    public void deletar(int id) {
        Optional<Campanha> campanhaOpt = this.campanhaRepository.findById(id);
        this.campanhaRepository.delete(campanhaOpt.get());
    }
}
