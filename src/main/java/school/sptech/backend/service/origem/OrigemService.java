package school.sptech.backend.service.origem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.domain.origem.Origem;
import school.sptech.backend.domain.origem.repository.OrigemRepository;
import school.sptech.backend.exception.NaoEncontradoException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrigemService {

    private final OrigemRepository repository;

    public Origem criar(Origem origem) {
        return this.repository.save(origem);
    }

    public List<Origem> listar() {
        final List<Origem> origens = this.repository.findAll();

        if (origens.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        return origens;
    }

    public Origem porId(int id) {
        return this.repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Origem")
        );
    }


    public Origem atualizar(Origem origemAtualizado, int id) {
        this.repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Origem")
        );

        origemAtualizado.setId(id);
        return this.repository.save(origemAtualizado);
    }

    public void deletar(int id) {
         if (!repository.existsById(id)){
             throw new NaoEncontradoException("Origem");
         }

        this.repository.deleteById(id);
    }
}
