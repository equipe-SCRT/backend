package school.sptech.backend.service.tipocesta;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produto.repository.ProdutoRepository;
import school.sptech.backend.domain.tipocesta.TipoCesta;
import school.sptech.backend.domain.tipocesta.repository.TipoCestaRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.BaseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoCestaService implements BaseService<TipoCesta, Integer> {
    private final TipoCestaRepository tipoCestaRepository;
    public TipoCesta porId(Integer id){
        return tipoCestaRepository.findById(id).orElseThrow(
                ()-> new NaoEncontradoException("Tipo Cesta")
        );
    }
    public List<TipoCesta> listar(){
        return tipoCestaRepository.findAll();
    }

    public TipoCesta criar(TipoCesta tipoCesta){

        return tipoCestaRepository.save(tipoCesta);
    }

    public TipoCesta atualizar(Integer id, TipoCesta novo){
        TipoCesta tipoCesta = porId(id);
        novo.setId(tipoCesta.getId());
        return tipoCestaRepository.save(novo);
    }

    public Void deletar(Integer id){
        tipoCestaRepository.delete(porId(id));
        return null;
    }
}
