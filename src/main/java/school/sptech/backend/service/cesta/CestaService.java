package school.sptech.backend.service.cesta;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.domain.cesta.repository.CestaRepository;
import school.sptech.backend.domain.tipocesta.TipoCesta;
import school.sptech.backend.domain.tipocesta.repository.TipoCestaRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.BaseService;
import school.sptech.backend.service.cesta.dto.CestaMapper;
import school.sptech.backend.service.tipocesta.TipoCestaService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CestaService implements BaseService<Cesta, Integer> {
    private final CestaRepository cestaRepository;
    private final TipoCestaService tipoCestaService;

    public Cesta porId(Integer id){
        return (cestaRepository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Cesta")
        ));
    }

    public List<Cesta> listar(){
        return cestaRepository.findAll();
    }

    public Cesta criar(Cesta cesta){
        cesta.setTipoCesta(tipoCestaService.porId(cesta.getTipoCesta().getId()));
        return cestaRepository.save(cesta);
    }

    public Cesta atualizar(Integer id, Cesta novo){
        Cesta cesta = porId(id);
        novo.setId(cesta.getId());
        return cestaRepository.save(novo);
    }

    public Void deletar(Integer id){
        cestaRepository.delete(porId(id));
        return null;
    }

    public Integer qtdMesAtual(){
        return cestaRepository.qtdPorMesAno(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
    }

    public Integer qtdItemsCestaPrincipal(){
        return cestaRepository.qtdItemsCestaPrincipal();
    }



}
