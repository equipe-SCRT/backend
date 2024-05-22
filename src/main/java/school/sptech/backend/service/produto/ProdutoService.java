package school.sptech.backend.service.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produto.repository.ProdutoRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.tipoproduto.TipoProdutoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    private final TipoProdutoService tipoProdutoService;

    public Produto criar(Produto novoProduto, Integer tipoProdutoId){
        novoProduto.setTipoProduto(tipoProdutoService.porId(tipoProdutoId));
        return repository.save(novoProduto);
    }

    public List<Produto> listar(){
        return repository.findAll();
    }

    public Produto porId(Integer id){
        return repository.findById(id).orElseThrow(()-> new NaoEncontradoException("Produto"));
    }

    public Produto atualizar(Produto produtoAtualizado, Integer id){
        porId(id);
        produtoAtualizado.setId(id);
        return repository.save(produtoAtualizado);
    }

    public void deletar(Integer id){
        porId(id);
        repository.deleteById(id);
    }
}
