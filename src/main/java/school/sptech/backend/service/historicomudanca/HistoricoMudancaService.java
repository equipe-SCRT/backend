package school.sptech.backend.service.historicomudanca;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import school.sptech.backend.domain.historicomudanca.HistoricoMudanca;
import school.sptech.backend.domain.historicomudanca.repository.HistoricoMudancaRepository;
import school.sptech.backend.domain.usuario.entity.Usuario;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.usuario.UsuarioService;

@Service

public class HistoricoMudancaService {
    
    @Autowired
    private  HistoricoMudancaRepository repository;
    @Autowired
    private  UsuarioService usuarioService;


    public HistoricoMudanca criar(HistoricoMudanca novoHistorico, Long idUsuario){

        
        novoHistorico.setUsuario(usuarioService.porId(idUsuario));

        repository.save(novoHistorico);

        return  novoHistorico;
    }

    public List<HistoricoMudanca> listar(){
        final List<HistoricoMudanca> historicos = repository.findAll();

       return historicos;
    }

    public HistoricoMudanca porId(int id){
        return repository.findById(id).orElseThrow(
            ()-> new NaoEncontradoException("Historico Mudança")
        );
    }

    public HistoricoMudanca atualizar(HistoricoMudanca historico, int IdHistoricoMudanca, Long idUsuario){

        Usuario usuario = usuarioService.porId(idUsuario);

        HistoricoMudanca historicoAtualizado = new HistoricoMudanca();
        historicoAtualizado.setIdHistoricoMudanca(IdHistoricoMudanca);
        historicoAtualizado.setDataHora(historico.getDataHora());
        historicoAtualizado.setUsuario(usuario);
        repository.save(historicoAtualizado);

        return historicoAtualizado;

    }

    public void deletar(int id){
        repository.delete(porId(id));
    }

}
