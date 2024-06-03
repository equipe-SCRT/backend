package school.sptech.backend.service.metrica;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.domain.metrica.repository.MetricaRepository;
import school.sptech.backend.domain.usuario.entity.Usuario;
import school.sptech.backend.domain.usuario.repository.UsuarioRepository;
import school.sptech.backend.exception.NaoEncontradoException;

import school.sptech.backend.service.metrica.dto.MetricaAtualizacaoDto;


@Service

public class MetricaService {


    @Autowired
    private MetricaRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public Metrica criar(Metrica metricaCriacao, Long idUsuario){

        metricaCriacao.setUsuario(usuarioRepository.findById(idUsuario).get());
        repository.save(metricaCriacao);
        return metricaCriacao;
    }

    public List<Metrica> listar(){

        final List<Metrica> metricas = repository.findAll();

        return metricas;
    }

    public Metrica porId(int id){
        return repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Metrica"));
    }


    public Metrica atualizar(Metrica metricaAtualizacao){

        repository.save(metricaAtualizacao);

        return metricaAtualizacao;

    }

    public void deletar(int id){
        repository.delete(porId(id));
    }
}
