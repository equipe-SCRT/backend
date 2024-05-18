package school.sptech.backend.service.condominio;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.domain.condominio.Condominio;
import school.sptech.backend.domain.condominio.repository.CondominioRepository;
import school.sptech.backend.service.condominio.dto.CondominioAtualizacaoDto;
import school.sptech.backend.service.condominio.dto.CondominioCriacaoDto;
import school.sptech.backend.service.condominio.dto.CondominioListagemDto;
import school.sptech.backend.service.condominio.dto.CondominioMapper;

import java.util.List;
import java.util.Optional;

@Service
public class CondominioService {

    @Autowired
    private CondominioRepository repository;

    @Autowired
    private CondominioMapper mapper;

    public void criar(CondominioCriacaoDto dto) {
        final Condominio novoCondominio = mapper.toEntity(dto);
        this.repository.save(novoCondominio);
    }

    public List<CondominioListagemDto> listar() {
        final List<Condominio> condominios = this.repository.findAll();

        if (condominios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        final List<CondominioListagemDto> dto = mapper.toDto(condominios);

        return dto;
    }

    public CondominioListagemDto porId(int id) {
        final Optional<Condominio> condominioOpt = repository.findById(id);

        if (condominioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        final CondominioListagemDto dto = mapper.toDto(condominioOpt.get());

        return dto;
    }

    public CondominioListagemDto porNome(String nome) {
        final Optional<Condominio> condominioOpt = repository.findByNome(nome);

        if (condominioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        final CondominioListagemDto dto = mapper.toDto(condominioOpt.get());

        return dto;
    }

    public CondominioListagemDto atualizar(CondominioAtualizacaoDto condominioAtualizado, int id) {
        final Optional<Condominio> condominioOpt = repository.findById(id);

        if (condominioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        final Condominio condominio = mapper.atualizacaoDto(condominioAtualizado, condominioOpt.get());

        final CondominioListagemDto dto = mapper.toDto(condominio);

        this.repository.save(condominio);

        return dto;
    }

    public void deletar(int id) {
        final Optional<Condominio> condominioOpt = repository.findById(id);

        if (condominioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        this.repository.deleteById(id);
    }
}
