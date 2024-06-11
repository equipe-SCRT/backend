package school.sptech.backend.service.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produto.repository.ProdutoRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.tipoproduto.TipoProdutoService;
import school.sptech.backend.service.unidademedida.UnidadeMedidaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    private final TipoProdutoService tipoProdutoService;

    private final UnidadeMedidaService unidadeMedidaService;

    public Produto criar(Produto novoProduto, Integer tipoProdutoId, Integer unidadeMedidaId){
        novoProduto.setTipoProduto(tipoProdutoService.porId(tipoProdutoId));
        novoProduto.setUnidadeMedida(unidadeMedidaService.porId(unidadeMedidaId));
        return repository.save(novoProduto);
    }

    public List<Produto> listar(){
        return repository.findAll();
    }

    public Produto porId(Integer id){
        return repository.findById(id).orElseThrow(()-> new NaoEncontradoException("Produto"));
    }

    public List<Produto> buscaPorParteDoNome(String nome){
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Produto atualizar(Produto produtoAtualizado, Integer id){
        if (!repository.existsById(id)) {
            throw new NaoEncontradoException("Produto");
        }
        produtoAtualizado.setId(id);
        return repository.save(produtoAtualizado);
    }

    public void deletar(Integer id){
        if (!repository.existsById(id)) {
            throw new NaoEncontradoException("Produto");
        }
        repository.deleteById(id);
    }
}
