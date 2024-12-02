package school.sptech.backend.service.metrica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.domain.metrica.repository.MetricaRepository;
import school.sptech.backend.service.usuario.UsuarioService;
import school.sptech.backend.exception.NaoEncontradoException;

import school.sptech.backend.service.BaseService;


@Service

public class MetricaService implements BaseService<Metrica, Integer> {


    @Autowired
    private MetricaRepository repository;

    public Metrica criar(Metrica metricaCriacao){
        repository.save(metricaCriacao);
        return metricaCriacao;
    }

    public List<Metrica> listar(){

        final List<Metrica> metricas = repository.findAll();

        return metricas;
    }

    public Metrica porId(Integer id){
        return repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Metrica"));
    }

    public Metrica ultimoAdicionado(){
        return repository.findTop1ByOrderByIdDesc().orElseThrow(() -> new NaoEncontradoException("Metrica"));
    }


    public Metrica atualizar(Integer id, Metrica metricaAtualizacao){
        porId(id);
        return repository.save(metricaAtualizacao);

    }

    public Void deletar(Integer id){
        repository.delete(porId(id));
        return null;
    }
}
