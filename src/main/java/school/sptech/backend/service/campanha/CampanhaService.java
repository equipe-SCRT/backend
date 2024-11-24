package school.sptech.backend.service.campanha;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.campanha.repository.CampanhaRepository;
import school.sptech.backend.domain.campanha.repository.QtdDoacoesPorCampanhaRepository;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.tipocampanha.TipoCampanha;
import school.sptech.backend.service.BaseService;
import school.sptech.backend.service.produto.ProdutoService;
import school.sptech.backend.service.tipocampanha.TipoCampanhaService;
import school.sptech.backend.service.campanha.dto.CampanhaAtualizacaoDto;
import school.sptech.backend.service.campanha.dto.CampanhaCriacaoDto;
import school.sptech.backend.service.campanha.dto.CampanhaListagemDto;
import school.sptech.backend.service.campanha.dto.CampanhaMapper;
import school.sptech.backend.service.campanha.view.QtdDoacoesPorCampanha;
import school.sptech.backend.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.backend.service.produto.dto.ProdutoListagemDto;
import school.sptech.backend.service.produto.dto.ProdutoMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CampanhaService {

    private final ProdutoService produtoService;
    private final TipoCampanhaService tipoCampanhaService;
    private final CampanhaRepository campanhaRepository;
    private final QtdDoacoesPorCampanhaRepository qtdDoacoesPorCampanhaRepository;

    public Campanha criar(Campanha campanhaCriacao, Integer fkProduto, Integer fkTipoCampanha) {
        Produto produto = produtoService.porId(fkProduto);
        TipoCampanha tipoCampanha = tipoCampanhaService.porId(fkTipoCampanha);

        campanhaCriacao.setProduto(produto);
        campanhaCriacao.setTipoCampanha(tipoCampanha);

        return this.campanhaRepository.save(campanhaCriacao);
    }

    public List<Campanha> listar(){
        final List<Campanha> campanhas = this.campanhaRepository.findAll();

        if (campanhas.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        return campanhas;
    }

    public Campanha atualizar(Integer id,Campanha campanhaAtualizacao){

        this.campanhaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        campanhaAtualizacao.setId(id);
        return this.campanhaRepository.save(campanhaAtualizacao);
    }
    public Campanha porId(Integer id){

        return this.campanhaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public Void deletar(Integer id){
        this.campanhaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        this.campanhaRepository.deleteById(id);
        return null;
    }

    public List<QtdDoacoesPorCampanha> qtdDoacoesPorCampanhas(String nome, LocalDate inicio, LocalDate fim){
        return qtdDoacoesPorCampanhaRepository.findByNomeAndDataCampanhaBetween(nome, inicio, fim);
    }

    public List<Campanha> porData(LocalDate data){
        return campanhaRepository.findByDataCampanha(data);
    }
}
