package school.sptech.backend.service.tipocampanha;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.tipocampanha.TipoCampanha;
import school.sptech.backend.domain.tipocampanha.repository.TipoCampanhaRepository;
import school.sptech.backend.service.BaseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoCampanhaService implements BaseService<TipoCampanha, Integer> {

    private final TipoCampanhaRepository tipoCampanhaRepository;

    public TipoCampanha criar(TipoCampanha tipoCampanhaCriacao) {
        System.out.println("obj" + tipoCampanhaCriacao.toString());

        return tipoCampanhaRepository.save(tipoCampanhaCriacao);
    }

    public List<TipoCampanha> listar(){
        final List<TipoCampanha> tipoCampanhas = this.tipoCampanhaRepository.findAll();

        if (tipoCampanhas.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        return tipoCampanhas;
    }

    public TipoCampanha atualizar(Integer id,TipoCampanha tipoCampanhaAtualizacao){

        this.tipoCampanhaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        tipoCampanhaAtualizacao.setId(id);
        return this.tipoCampanhaRepository.save(tipoCampanhaAtualizacao);
    }

    public TipoCampanha porId(Integer id){

        return this.tipoCampanhaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public Void deletar(Integer id){
        this.tipoCampanhaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        this.tipoCampanhaRepository.deleteById(id);
        return null;
    }
}
