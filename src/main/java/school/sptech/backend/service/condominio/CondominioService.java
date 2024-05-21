package school.sptech.backend.service.condominio;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.domain.condominio.Condominio;
import school.sptech.backend.domain.condominio.repository.CondominioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CondominioService {

    private final CondominioRepository repository;

    public Condominio criar(Condominio condominio) {
        return this.repository.save(condominio);
    }

    public List<Condominio> listar() {
        final List<Condominio> condominios = this.repository.findAll();

        if (condominios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        return condominios;
    }

    public Condominio porId(int id) {
        return this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public Condominio porNome(String nome) {
        return this.repository.findByNomeIgnoreCase(nome).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public Condominio atualizar(Condominio condominioAtualizado, int id) {
        this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        condominioAtualizado.setId(id);
        return this.repository.save(condominioAtualizado);
    }

    public void deletar(int id) {
        this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        this.repository.deleteById(id);
    }
}
