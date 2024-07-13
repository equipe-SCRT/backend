package school.sptech.backend.service.produtocesta;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import school.sptech.backend.domain.produtocesta.ProdutoCesta;
import school.sptech.backend.domain.produtocesta.repository.ProdutoCestaRepository;

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

        produtoCesta.setId(id);
        return repository.save(produtoCesta);
    }




}
