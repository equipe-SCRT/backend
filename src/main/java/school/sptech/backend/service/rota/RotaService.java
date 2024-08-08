package school.sptech.backend.service.rota;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.rota.Rota;
import school.sptech.backend.domain.rota.repository.RotaRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.BaseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RotaService implements BaseService<Rota, Integer> {

    private final RotaRepository repository;

    public Rota criar(Rota rota) {
        return this.repository.save(rota);
    }

    public List<Rota> listar() {
        return this.repository.findAll();
    }

    public Rota porId(Integer id) {
        return this.repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Rota")
        );
    }

    public Rota porNome(String nome) {
        return this.repository.findByNomeIgnoreCase(nome).orElseThrow(
                () -> new NaoEncontradoException("Rota")
        );
    }

    public Rota atualizar(Integer id, Rota rotaAtualizada) {
        if (!this.repository.existsById(id)) {
            throw new NaoEncontradoException("Rota");
        }

        rotaAtualizada.setId(id);
        return this.repository.save(rotaAtualizada);
    }

    public Void deletar(Integer id) {
        if (!this.repository.existsById(id)) {
            throw new NaoEncontradoException("Rota");
        }

        this.repository.deleteById(id);
        return null;
    }
}
