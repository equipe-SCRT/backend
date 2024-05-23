package school.sptech.backend.service.historicomudanca;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void criar(HistoricoMudancaCriacaoDto HistoricoMudancaCriacao){
        final HistoricoMudanca novoHistorico = HistoricoMudancaMapper.toEntity(HistoricoMudancaCriacao);
        repository.save(novoHistorico);
    }

    public List<HistoricoMudancaListagemDto> listar(){
        final List<HistoricoMudanca> historicos = repository.findAll();

       if (historicos.isEmpty()) {
          throw new ResponseStatusException(HttpStatus.NO_CONTENT);
       }

       return HistoricoMudancaMapper.toDto(historicos);
    }

    public HistoricoMudanca porId(int id){
        return repository.findById(id).orElseThrow(
            ()-> new NaoEncontradoException("Historico Mudança")
        );
    }

    public HistoricoMudancaListagemDto atualizar(HistoricoMudancaAtualizacaoDto historico, long fk_usuario){

        Optional<Usuario> usuario = usuarioRepository.findById(fk_usuario);

        HistoricoMudanca historicoAtualizado = new HistoricoMudanca();

        historicoAtualizado.setId_historico_mudanca(historico.getId_historico_mudanca());
        historicoAtualizado.setData_hora(historico.getData_hora());
        historicoAtualizado.setUsuario(usuario.get());
        repository.save(historicoAtualizado);

        return HistoricoMudancaMapper.toDto(historicoAtualizado);

    }

    public void deletar(int id){
        repository.delete(porId(id));
    }

}
