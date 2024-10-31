package school.sptech.backend.service.produtocesta;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produtocesta.ProdutoCesta;
import school.sptech.backend.domain.produtocesta.repository.ProdutoCestaRepository;
import school.sptech.backend.domain.tipocesta.TipoCesta;
import school.sptech.backend.service.BaseService;
import school.sptech.backend.service.produto.ProdutoService;
import school.sptech.backend.service.produtocesta.dto.ProdutoCestaCriacaoDto;
import school.sptech.backend.service.tipocesta.TipoCestaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoCestaService {
    private final ProdutoCestaRepository repository;
    private final ProdutoService produtoService;
    private final TipoCestaService tipoCestaService;

    public ProdutoCesta criar(ProdutoCesta produtoCesta, Integer produtoId, Integer tipoCestaId){

        Produto produto = produtoService.porId(produtoId);
        TipoCesta tipoCesta = tipoCestaService.porId(tipoCestaId);
        produtoCesta.setProduto(produto);
        produtoCesta.setTipoCesta(tipoCesta);

        return repository.save(produtoCesta);
    }

    public List<ProdutoCesta> listar(){
        return repository.findAll();
    }

    public ProdutoCesta porId(Integer id){
        Optional<ProdutoCesta> prod = repository.findById(id);

        if(prod.isEmpty())
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);

        return prod.get();
    }

    public Void deletar(Integer id){
        if (!repository.existsById(id))
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);

        repository.deleteById(id);
        return null;
    }

    public ProdutoCesta atualizar(Integer id, ProdutoCesta produtoCesta){
        if (!repository.existsById(id))
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);

        produtoCesta.setId(id);
        return repository.save(produtoCesta);
    }




}
