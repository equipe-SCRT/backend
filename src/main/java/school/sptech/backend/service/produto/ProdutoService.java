package school.sptech.backend.service.produto;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produto.repository.ProdutoRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.BaseService;
import school.sptech.backend.service.tipoproduto.TipoProdutoService;
import school.sptech.backend.service.unidademedida.UnidadeMedidaService;
import school.sptech.backend.view.alimentosarrecadadospormes.AlimentosArrecadadosPorMes;
import school.sptech.backend.view.alimentosarrecadadospormes.repository.AlimentosArrecadadosPorMesRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService implements BaseService<Produto, Integer> {

    private final ProdutoRepository repository;

    private final TipoProdutoService tipoProdutoService;

    private final UnidadeMedidaService unidadeMedidaService;

    private final AlimentosArrecadadosPorMesRepository alimentosArrecadadosPorMesRepository;

    public Produto criar(Produto novoProduto){
        novoProduto.setTipoProduto(tipoProdutoService.porId(novoProduto.getTipoProduto().getId()));
        novoProduto.setUnidadeMedida(unidadeMedidaService.porId(novoProduto.getUnidadeMedida().getId()));
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

    public Produto atualizar(Integer id, Produto produtoAtualizado){
        Optional<Produto> byId = repository.findById(id);
        if(byId.isEmpty()){
            throw new NaoEncontradoException("Produto");
        }
        Produto produto = byId.get();
        produtoAtualizado.setTipoProduto(tipoProdutoService.porId(produtoAtualizado.getTipoProduto().getId()));
        produtoAtualizado.setUnidadeMedida(produto.getUnidadeMedida());
        produtoAtualizado.setQtdUnidadeMedida(produto.getQtdUnidadeMedida());
        produtoAtualizado.setId(id);
        return repository.save(produtoAtualizado);
    }

    public Void deletar(Integer id){
        if (!repository.existsById(id)) {
            throw new NaoEncontradoException("Produto");
        }
        repository.deleteById(id);
        return null;
    }

    public List<AlimentosArrecadadosPorMes>  alimentosArrecadadosPorMes(){
        return alimentosArrecadadosPorMesRepository.findAll();
    }
}
