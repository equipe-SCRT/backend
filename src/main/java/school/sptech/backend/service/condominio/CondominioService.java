package school.sptech.backend.service.condominio;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.domain.condominio.Condominio;
import school.sptech.backend.domain.condominio.repository.CondominioRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.endereco.EnderecoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CondominioService {

    private final CondominioRepository repository;

    private final EnderecoService enderecoService;

    public Condominio criar(Condominio condominio, Integer enderecoId) {
        condominio.setEndereco(enderecoService.porId(enderecoId));
        return this.repository.save(condominio);
    }

    public List<Condominio> listar() {
        return this.repository.findAll();
    }

    public Condominio porId(int id) {
        return this.repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Condomínio")
        );
    }

    public Condominio porNome(String nome) {
        return this.repository.findByNomeIgnoreCase(nome).orElseThrow(
                () -> new NaoEncontradoException("Condomínio")
        );
    }

    public Condominio atualizar(Condominio condominioAtualizado, int id) {
        if (!this.repository.existsById(id)) {
            throw new NaoEncontradoException("Condomínio");
        }

        condominioAtualizado.setId(id);
        return this.repository.save(condominioAtualizado);
    }

    public void deletar(int id) {
        if (!this.repository.existsById(id)) {
            throw new NaoEncontradoException("Condomínio");
        }

        this.repository.deleteById(id);
    }
}
