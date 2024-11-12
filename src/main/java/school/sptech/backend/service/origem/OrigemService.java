package school.sptech.backend.service.origem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.domain.origem.Origem;
import school.sptech.backend.domain.origem.repository.OrigemRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.BaseService;
import school.sptech.backend.service.campanha.CampanhaService;
import school.sptech.backend.service.condominio.CondominioService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrigemService implements BaseService<Origem, Integer> {

    private final OrigemRepository repository;
    private final CampanhaService campanhaService;
    private final CondominioService condominioService;

    public Origem criar(Origem origem) {
        if(origem.getCampanha() != null){
            origem.setCampanha(campanhaService.porId(origem.getCampanha().getId()));
        } else if (origem.getCondominio() != null){
            origem.setCondominio(condominioService.porId(origem.getCondominio().getId()));
        }
        return this.repository.save(origem);
    }

    public List<Origem> listar() {
        final List<Origem> origens = this.repository.findAll();

        if (origens.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        return origens;
    }

    public Origem porId(Integer id) {
        return this.repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Origem")
        );
    }


    public Origem atualizar(Integer id, Origem origemAtualizado) {
        this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        origemAtualizado.setId(id);
        return this.repository.save(origemAtualizado);
    }

    public Void deletar(Integer id) {
        this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        this.repository.deleteById(id);
        return null;
    }
}
