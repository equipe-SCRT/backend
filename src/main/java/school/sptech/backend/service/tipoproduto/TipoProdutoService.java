package school.sptech.backend.service.tipoproduto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.tipoproduto.TipoProduto;
import school.sptech.backend.domain.tipoproduto.repository.TipoProdutoRepository;
import school.sptech.backend.exception.NaoEncontradoException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoProdutoService {

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

    public TipoProduto atualizar(TipoProduto tipoProdutoAtualizado, Integer id){
        porId(id);
        tipoProdutoAtualizado.setId(id);
        return repository.save(tipoProdutoAtualizado);
    }

    public void deletar(Integer id){
        porId(id);
        repository.deleteById(id);
    }
}
