package school.sptech.backend.service.unidademedida;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.unidademedida.UnidadeMedida;
import school.sptech.backend.domain.unidademedida.repository.UnidadeMedidaRepository;
import school.sptech.backend.exception.NaoEncontradoException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadeMedidaService {

    private final UnidadeMedidaRepository repository;

    public UnidadeMedida criar(UnidadeMedida novaUnidadeMedida){
        return repository.save(novaUnidadeMedida);
    }

    public List<UnidadeMedida> listar(){
        return repository.findAll();
    }

    public UnidadeMedida porId(Integer id){
        return repository.findById(id).orElseThrow(()-> new NaoEncontradoException("Unidade de Medida"));
    }

    public UnidadeMedida atualizar(UnidadeMedida produtoAtualizado, Integer id){
        if (!repository.existsById(id)){
            throw new NaoEncontradoException("Unidade de Medida");
        }
        produtoAtualizado.setId(id);
        return repository.save(produtoAtualizado);
    }

    public void deletar(Integer id){
        if (!repository.existsById(id)){
            throw new NaoEncontradoException("Unidade de Medida");
        }
        repository.deleteById(id);
    }
}
