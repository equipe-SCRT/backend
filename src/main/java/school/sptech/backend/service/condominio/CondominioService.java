package school.sptech.backend.service.condominio;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.domain.condominio.Condominio;
import school.sptech.backend.domain.condominio.repository.CondominioRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.BaseService;
import school.sptech.backend.service.endereco.EnderecoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CondominioService implements BaseService<Condominio, Integer> {

    private final CondominioRepository repository;

    private final EnderecoService enderecoService;

    public Condominio criar(Condominio condominio) {
        condominio.setEndereco(enderecoService.porId(condominio.getEndereco().getId()));
        return this.repository.save(condominio);
    }

    public List<Condominio> listar() {
        return this.repository.findAll();
    }

    public Condominio porId(Integer id) {
        return this.repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Condomínio")
        );
    }

    public Condominio porNome(String nome) {
        return this.repository.findByNomeIgnoreCase(nome).orElseThrow(
                () -> new NaoEncontradoException("Condomínio")
        );
    }

    public Condominio atualizar(Integer id, Condominio condominioAtualizado) {
        if (!this.repository.existsById(id)) {
            throw new NaoEncontradoException("Condomínio");
        }

        condominioAtualizado.setId(id);
        return this.repository.save(condominioAtualizado);
    }

    public Void deletar(Integer id) {
        if (!this.repository.existsById(id)) {
            throw new NaoEncontradoException("Condomínio");
        }

        this.repository.deleteById(id);
        return null;
    }
}
