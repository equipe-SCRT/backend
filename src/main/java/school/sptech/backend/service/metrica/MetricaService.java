package school.sptech.backend.service.metrica;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import school.sptech.backend.domain.historicomudanca.HistoricoMudanca;
import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.domain.metrica.repository.MetricaRepository;
import school.sptech.backend.domain.usuario.entity.Usuario;
import school.sptech.backend.domain.usuario.repository.UsuarioRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaCriacaoDto;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaMapper;
import school.sptech.backend.service.metrica.dto.MetricaAtualizacaoDto;
import school.sptech.backend.service.metrica.dto.MetricaCriacaoDto;
import school.sptech.backend.service.metrica.dto.MetricaListagemDto;
import school.sptech.backend.service.metrica.dto.MetricaMapper;

@Service
public class MetricaService {
    
    @Autowired
    private MetricaRepository repository;
    private UsuarioRepository usuarioRepository;

    public void criar(Metrica metricaCriacao){
        repository.save(metricaCriacao);
    }

    public List<Metrica> listar(){

        final List<Metrica> metricas = repository.findAll();

        if (metricas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        return metricas;
    }

    public Metrica porId(int id){
        return repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Metrica"));
    }

    public Metrica atualizar(MetricaAtualizacaoDto metricaAtualizacao, int id){

        Optional<Usuario> usuario = usuarioRepository.findById(metricaAtualizacao.getFkUsuario());

        if (usuario.isEmpty()) {
            throw new NaoEncontradoException("Usuario");
        }

        Metrica metricaAtualizado = new Metrica();

        metricaAtualizado.setIdMetrica(id);
        metricaAtualizado.setAlteracao(metricaAtualizacao.getAlteracao());
        metricaAtualizado.setUsuario(usuario.get());
        repository.save(metricaAtualizado);

        return metricaAtualizado;
    }

    public void deletar(int id){
        repository.delete(porId(id));
    }
}
