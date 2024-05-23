package school.sptech.backend.service.historicomudanca;

import java.util.List;
import java.util.Optional;

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

@Service
public class HistoricoMudancaService {
    
    @Autowired
    private HistoricoMudancaRepository repository;
    private UsuarioRepository usuarioRepository;

    public void criar(HistoricoMudanca novoHistorico){
        repository.save(novoHistorico);
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

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        HistoricoMudanca historicoAtualizado = new HistoricoMudanca();
        historicoAtualizado.setId_historico_mudanca(historico.getId_historico_mudanca());
        historicoAtualizado.setData_hora(historico.getData_hora());
        historicoAtualizado.setUsuario(usuario.get());
        repository.save(historicoAtualizado);

        return historicoAtualizado;

    }

    public void deletar(int id){
        repository.delete(porId(id));
    }

}
