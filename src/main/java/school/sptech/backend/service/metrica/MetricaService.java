package school.sptech.backend.service.metrica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.domain.metrica.repository.MetricaRepository;
import school.sptech.backend.domain.usuario.repository.UsuarioRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaCriacaoDto;
import school.sptech.backend.service.metrica.dto.MetricaCriacaoDto;
import school.sptech.backend.service.metrica.dto.MetricaListagemDto;
import school.sptech.backend.service.metrica.dto.MetricaMapper;

@Service
public class MetricaService {
    
    @Autowired
    private MetricaRepository repository;
    private UsuarioRepository alunoRepository;

    public void criar(MetricaCriacaoDto metricaCriacao){
        final Metrica MetricaCriada = MetricaMapper.toEntity(metricaCriacao);

        repository.save(MetricaCriada);
    }

    public List<MetricaListagemDto> listar(){

        final List<Metrica> metricas = repository.findAll();

        if (metricas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        return MetricaMapper.toDto(metricas);
    }

    public Metrica porId(int id){
        return repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Metrica"));
    }

    public Metrica atualizar(){
        return null;
    }

    public void deletar(int id){
        repository.delete(porId(id));
    }
}
