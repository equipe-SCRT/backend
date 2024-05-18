package school.sptech.backend.service.rota;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.domain.rota.Rota;
import school.sptech.backend.domain.rota.repository.RotaRepository;
import school.sptech.backend.service.rota.dto.RotaAtualizacaoDto;
import school.sptech.backend.service.rota.dto.RotaCriacaoDto;
import school.sptech.backend.service.rota.dto.RotaListagemDto;
import school.sptech.backend.service.rota.dto.RotaMapper;

import java.util.List;
import java.util.Optional;

@Service
public class RotaService {

    @Autowired
    private RotaRepository repository;

    @Autowired
    private RotaMapper mapper;

    public void criar(RotaCriacaoDto dto) {
        final Rota novaRota = mapper.toEntity(dto);
        this.repository.save(novaRota);
    }

    public List<RotaListagemDto> listar() {
        final List<Rota> rotas = this.repository.findAll();

        if (rotas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        final List<RotaListagemDto> dto = mapper.toDto(rotas);

        return dto;
    }

    public RotaListagemDto porId(int id) {
        final Optional<Rota> rotaOpt = repository.findById(id);

        if (rotaOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        final RotaListagemDto dto = mapper.toDto(rotaOpt.get());

        return dto;
    }

    public RotaListagemDto porNome(String nome) {
        final Optional<Rota> rotaOpt = repository.findByNomeIgnoreCase(nome);

        if (rotaOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        final RotaListagemDto dto = mapper.toDto(rotaOpt.get());

        return dto;
    }

    public RotaListagemDto atualizar(RotaAtualizacaoDto rotaAtualizada, int id) {
        final Optional<Rota> rotaOpt = repository.findById(id);

        if (rotaOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        final Rota rota = mapper.atualizacaoDto(rotaAtualizada, rotaOpt.get());

        final RotaListagemDto dto = mapper.toDto(rota);

        this.repository.save(rota);

        return dto;
    }

    public void deletar(int id) {
        final Optional<Rota> rotaOpt = repository.findById(id);

        if (rotaOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        this.repository.deleteById(id);
    }
}
