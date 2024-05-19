package school.sptech.backend.service.campanha;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CampanhaService {


    private final CampanhaRepository campanhaRepository;

    public Campanha criar(Campanha campanhaCriacao) {
        return this.campanhaRepository.save(campanhaCriacao);
    }

    public List<Campanha> listar(){
        final List<Campanha> campanhas = this.campanhaRepository.findAll();

        if (campanhas.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        return campanhas;
    }

    public Campanha atualizar(Campanha campanhaAtualizacao, int id){

        this.campanhaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        campanhaAtualizacao.setId(id);
        return this.campanhaRepository.save(campanhaAtualizacao);
    }
    public Campanha porId(int id){

        return this.campanhaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public void deletar(int id){
        this.campanhaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        this.campanhaRepository.deleteById(id);
    }
}
