package school.sptech.backend.service.tipocesta;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.cesta.repository.CestaRepository;
import school.sptech.backend.domain.tipocesta.TipoCesta;
import school.sptech.backend.domain.tipocesta.repository.TipoCestaRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.cesta.CestaService;
import school.sptech.backend.service.produtocesta.ProdutoCestaService;
import school.sptech.backend.service.tipocesta.dto.TipoCestaCriacaoDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoCestaService {
    private final TipoCestaRepository tipoCestaRepository;
    private final CestaRepository cestaRepository;

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

    @Transactional
    public Void deletar(Integer id){
        cestaRepository.deleteCestaByTipoCestaId(id);
        tipoCestaRepository.delete(porId(id));
        return null;
    }
}
