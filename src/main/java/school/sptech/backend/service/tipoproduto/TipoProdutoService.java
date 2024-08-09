package school.sptech.backend.service.tipoproduto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.tipoproduto.TipoProduto;
import school.sptech.backend.domain.tipoproduto.repository.TipoProdutoRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.BaseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoProdutoService implements BaseService<TipoProduto, Integer> {

    private final TipoProdutoRepository repository;

    public TipoProduto criar(TipoProduto novoTipoProduto){
        return repository.save(novoTipoProduto);
    }


    public TipoProduto porId(Integer id){
        return repository.findById(id).orElseThrow(()-> new NaoEncontradoException("Tipo Produto"));
    }

    public List<TipoProduto> listar(){
        return repository.findAll();
    }

    public TipoProduto atualizar(Integer id, TipoProduto tipoProdutoAtualizado){
        if (!repository.existsById(id)){
            throw new NaoEncontradoException("Tipo Produto");
        }
        tipoProdutoAtualizado.setId(id);
        return repository.save(tipoProdutoAtualizado);
    }

    public Void deletar(Integer id){
        if (!repository.existsById(id)){
            throw new NaoEncontradoException("Tipo Produto");
        }

        repository.deleteById(id);
        return null;
    }
}
