package school.sptech.backend.service.historicomudanca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.sptech.backend.domain.historicomudanca.HistoricoMudanca;
import school.sptech.backend.domain.historicomudanca.repository.HistoricoMudancaRepository;
import school.sptech.backend.domain.usuario.Usuario;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.BaseService;
import school.sptech.backend.service.usuario.UsuarioService;

@Service

public class HistoricoMudancaService implements BaseService<HistoricoMudanca, Integer> {
    
    @Autowired
    private  HistoricoMudancaRepository repository;
    @Autowired
    private  UsuarioService usuarioService;


    public HistoricoMudanca criar(HistoricoMudanca novoHistorico){

        novoHistorico.setUsuario(usuarioService.porId(novoHistorico.getUsuario().getIdUsuario()));
        repository.save(novoHistorico);

        return  novoHistorico;
    }

    public List<HistoricoMudanca> listar(){
        final List<HistoricoMudanca> historicos = repository.findAll();

       return historicos;
    }

    public HistoricoMudanca porId(Integer id){
        return repository.findById(id).orElseThrow(
            ()-> new NaoEncontradoException("Historico Mudança")
        );
    }

    public HistoricoMudanca atualizar(Integer IdHistoricoMudanca, HistoricoMudanca historico){

        Usuario usuario = usuarioService.porId(historico.getUsuario().getIdUsuario());

        HistoricoMudanca historicoAtualizado = new HistoricoMudanca();
        historicoAtualizado.setId(IdHistoricoMudanca);
        historicoAtualizado.setDataHora(historico.getDataHora());
        historicoAtualizado.setUsuario(usuario);
        repository.save(historicoAtualizado);

        return historicoAtualizado;

    }

    public Void deletar(Integer id){
        repository.delete(porId(id));
        return null;
    }

}
