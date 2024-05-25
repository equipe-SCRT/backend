package school.sptech.backend.service.historicomudanca;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import school.sptech.backend.domain.historicomudanca.HistoricoMudanca;
import school.sptech.backend.domain.historicomudanca.repository.HistoricoMudancaRepository;
import school.sptech.backend.domain.usuario.entity.Usuario;
import school.sptech.backend.domain.usuario.repository.UsuarioRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaAtualizacaoDto;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaCriacaoDto;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaListagemDto;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaMapper;
import school.sptech.backend.service.usuario.UsuarioService;

@Service
@RequiredArgsConstructor
public class HistoricoMudancaService {
    

    private final HistoricoMudancaRepository repository;
    private final UsuarioService usuarioService;


    public HistoricoMudanca criar(HistoricoMudanca novoHistorico, Long idUsuario){

        novoHistorico.setUsuario(usuarioService.porId(idUsuario));

        repository.save(novoHistorico);

        return  novoHistorico;
    }

    public List<HistoricoMudanca> listar(){
        final List<HistoricoMudanca> historicos = repository.findAll();

       if (historicos.isEmpty()) {
          throw new ResponseStatusException(HttpStatus.NO_CONTENT);
       }

       return historicos;
    }

    public HistoricoMudanca porId(int id){
        return repository.findById(id).orElseThrow(
            ()-> new NaoEncontradoException("Historico Mudança")
        );
    }

    public HistoricoMudanca atualizar(HistoricoMudanca historico, Long id){

        Usuario usuario = usuarioService.porId(id);

        HistoricoMudanca historicoAtualizado = new HistoricoMudanca();
        historicoAtualizado.setIdHistoricoMudanca(historico.getIdHistoricoMudanca());
        historicoAtualizado.setDataHora(historico.getDataHora());
        historicoAtualizado.setUsuario(usuario);
        repository.save(historicoAtualizado);

        return historicoAtualizado;

    }

    public void deletar(int id){
        repository.delete(porId(id));
    }

}
