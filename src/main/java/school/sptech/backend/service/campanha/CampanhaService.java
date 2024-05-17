package school.sptech.backend.service.campanha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.campanha.repository.CampanhaRepository;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.service.campanha.dto.CampanhaAtualizacaoDto;
import school.sptech.backend.service.campanha.dto.CampanhaCriacaoDto;
import school.sptech.backend.service.campanha.dto.CampanhaListagemDto;
import school.sptech.backend.service.campanha.dto.CampanhaMapper;
import school.sptech.backend.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.backend.service.produto.dto.ProdutoListagemDto;
import school.sptech.backend.service.produto.dto.ProdutoMapper;

import java.util.List;
import java.util.Optional;

@Service
public class CampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    public void criar(CampanhaCriacaoDto campanhaCriacao) {
        final Campanha novoCampanha = CampanhaMapper.toEntity(campanhaCriacao);
        this.campanhaRepository.save(novoCampanha);
    }

    public List<CampanhaListagemDto> listar(){
        final List<Campanha> campanhas = this.campanhaRepository.findAll();

        if (campanhas.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        final List<CampanhaListagemDto> dto = CampanhaMapper.toDto(campanhas);

        return dto;
    }

    public CampanhaListagemDto atualizar(CampanhaAtualizacaoDto campanhaAtualizacao, int id){
        final Optional<Campanha> campanhaOpt = this.campanhaRepository.findById(id);

        if (campanhaOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        final Campanha campanha = CampanhaMapper.atualizacaoDto(campanhaOpt.get(), campanhaAtualizacao);

        final CampanhaListagemDto dto = CampanhaMapper.toDto(campanha);

        this.campanhaRepository.save(campanha);

        return dto;
    }
    public CampanhaListagemDto porId(int id){
        final Optional<Campanha> campanhaOptional = this.campanhaRepository.findById(id);

        if (campanhaOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        final CampanhaListagemDto dto = CampanhaMapper.toDto(campanhaOptional.get());

        return dto;
    }

    public void deletar(int id){
        final Optional<Campanha> campanhaOpt = this.campanhaRepository.findById(id);

        if (campanhaOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        this.campanhaRepository.deleteById(id);
    }
}
