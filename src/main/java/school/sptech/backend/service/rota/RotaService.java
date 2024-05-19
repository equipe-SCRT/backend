package school.sptech.backend.service.rota;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.domain.rota.Rota;
import school.sptech.backend.domain.rota.repository.RotaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RotaService {

    private final RotaRepository repository;

    public Rota criar(Rota rota) {
        return this.repository.save(rota);
    }

    public List<Rota> listar() {
        final List<Rota> rotas = this.repository.findAll();

        if (rotas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        return rotas;
    }

    public Rota porId(int id) {
        return this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NO_CONTENT)
        );
    }

    public Rota porNome(String nome) {
        return this.repository.findByNomeIgnoreCase(nome).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NO_CONTENT)
        );
    }

    public Rota atualizar(Rota rotaAtualizada, int id) {
        this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NO_CONTENT)
        );

        rotaAtualizada.setId(id);
        return this.repository.save(rotaAtualizada);
    }

    public void deletar(int id) {
        this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        this.repository.deleteById(id);
    }
}
