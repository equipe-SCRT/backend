package school.sptech.backend.service.produtocesta;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produto.repository.ProdutoRepository;
import school.sptech.backend.domain.produtocesta.entity.ProdutoCesta;
import school.sptech.backend.domain.produtocesta.mapper.ProdutoCestaMapper;
import school.sptech.backend.domain.produtocesta.repository.ProdutoCestaRepository;
import school.sptech.backend.service.produtocesta.dto.ProdutoCestaEntityDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoCestaService {
    private ProdutoCestaRepository repository;

    public ProdutoCesta adicionar(ProdutoCesta produtoCesta){
        return repository.save(produtoCesta);
    }

    public List<ProdutoCesta> get(){
        List<ProdutoCesta> all = repository.findAll();

        return all;
    }

    public ProdutoCesta get(Integer id){
        Optional<ProdutoCesta> prod = repository.findById(id);

        if(prod.isEmpty())
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);

        return prod.get();
    }

    public void deletar(Integer id){
        if (!repository.existsById(id))
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);

        repository.deleteById(id);
    }

    public ProdutoCesta update(Integer id, ProdutoCesta produtoCesta){
        if (!repository.existsById(id))
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);

        produtoCesta.setIdProdutoCesta(id);
        return repository.save(produtoCesta);
    }


}
