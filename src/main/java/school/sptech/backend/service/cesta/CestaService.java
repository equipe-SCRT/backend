package school.sptech.backend.service.cesta;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.domain.cesta.repository.CestaRepository;
import school.sptech.backend.utils.exception.NaoEncontradoException;
import school.sptech.backend.service.cesta.dto.CestaMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CestaService {
    private final CestaRepository cestaRepository;
    private final CestaMapper cestaMapper;

    public Cesta porId(Long id){
        return (cestaRepository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Cesta")
        ));
    }

    public List<Cesta> listar(){
        return cestaRepository.findAll();
    }

    public Cesta salvar(Cesta cesta){
        return cestaRepository.save(cesta);
    }
}
