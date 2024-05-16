package school.sptech.backend.service.tipocesta;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.tipocesta.TipoCesta;
import school.sptech.backend.domain.tipocesta.repository.TipoCestaRepository;
import school.sptech.backend.utils.exception.NaoEncontradoException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoCestaService {
    private final TipoCestaRepository tipoCestaRepository;
    public TipoCesta porId(Long id){
        return tipoCestaRepository.findById(id).orElseThrow(
                ()-> new NaoEncontradoException("Tipo Cesta")
        );
    }
    public List<TipoCesta> listar(){
        return tipoCestaRepository.findAll();
    }

    public TipoCesta cadastrar(TipoCesta tipoCesta){
        return tipoCestaRepository.save(tipoCesta);
    }
}
