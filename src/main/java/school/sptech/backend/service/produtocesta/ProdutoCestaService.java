package school.sptech.backend.service.produtocesta;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produto.repository.ProdutoRepository;
import school.sptech.backend.domain.produtocesta.entity.ProdutoCesta;
import school.sptech.backend.domain.produtocesta.mapper.ProdutoCestaMapper;
import school.sptech.backend.domain.produtocesta.repository.ProdutoCestaRepository;
import school.sptech.backend.service.produtocesta.dto.ProdutoCestaEntityDto;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoCestaService {
    private ProdutoCestaRepository repository;
    private ProdutoRepository produtoRepository;

    public ProdutoCesta adicionar(ProdutoCesta produtoCesta){
        return repository.save(produtoCesta);
    }

    public List<ProdutoCestaEntityDto> get(){
        List<ProdutoCesta> all = repository.findAll();
        List<Produto> all1 = produtoRepository.findAll();
        List<ProdutoCesta> dtos = new ArrayList<>();

        List<ProdutoCestaEntityDto> entity = ProdutoCestaMapper.toEntity(all, all1);
        return entity;
    }
}
