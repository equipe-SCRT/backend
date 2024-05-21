package school.sptech.backend.service.produtounitario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.domain.produtounitario.repository.ProdutoUnitarioRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.produto.ProdutoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoUnitarioService {

    private final ProdutoUnitarioRepository repository;
    private final ProdutoService produtoService;

//    public ProdutoUnitario criar(ProdutoUnitario novoProdutoUnitario, Integer origemId, Integer unidadeMedidaId, Integer cestaId, Integer produtoId, Integer rotaId, Integer metricaId){
//        novoProdutoUnitario.setOrigem(service.porId(origemId));
//        novoProdutoUnitario.setUnidadeMedida(service.porId(origemId));
//        novoProdutoUnitario.setCesta(service.porId(origemId));
//        novoProdutoUnitario.setProduto(produtoService.porId(produtoId));
//        return repository.save(novoProdutoUnitario);
//    }

    public List<ProdutoUnitario> listar(){
        return repository.findAll();
    }

    public ProdutoUnitario porId(Integer id){
        return repository.findById(id).orElseThrow(()-> new NaoEncontradoException("Produto Unitário"));
    }

    public void deletar(int id){
        if (repository.existsById(id)){
           throw new NaoEncontradoException("Produto Unitário");
        }
        repository.deleteById(id);
    }
}
