package school.sptech.backend.service.produtocesta;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import school.sptech.backend.domain.produtocesta.ProdutoCesta;
import school.sptech.backend.domain.produtocesta.repository.ProdutoCestaRepository;
import school.sptech.backend.service.produto.ProdutoService;
import school.sptech.backend.service.produtocesta.dto.ProdutoCestaCriacaoDto;
import school.sptech.backend.service.tipocesta.TipoCestaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoCestaService {
    private final ProdutoCestaRepository repository;
    private final ProdutoService produtoService;
    private final TipoCestaService tipoCestaService;

    public ProdutoCesta criar(ProdutoCestaCriacaoDto produtoCestaCriacaoDto){
        ProdutoCesta produtoCesta = new ProdutoCesta();

        produtoCesta.setProduto(produtoService.porId(produtoCestaCriacaoDto.getProduto().getIdProduto()));
        produtoCesta.setTipoCesta(tipoCestaService.porId(produtoCestaCriacaoDto.getIdTipoCesta()));
        produtoCesta.setQtdProduto(produtoCestaCriacaoDto.getProduto().getQtdProduto());
        return repository.save(produtoCesta);
    }

    public List<ProdutoCesta> listar(){
        return repository.findAll();
    }

    public List<ProdutoCesta> porId(Integer idTipoCesta){
        List<ProdutoCesta> prod = repository.findByTipoCestaId(idTipoCesta);

        if(prod.isEmpty())
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);

        return prod;
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
