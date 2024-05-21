package school.sptech.backend.service.tipoproduto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.tipoproduto.TipoProduto;
import school.sptech.backend.domain.tipoproduto.repository.TipoProdutoRepository;
import school.sptech.backend.exception.NaoEncontradoException;

@Service
@RequiredArgsConstructor
public class TipoProdutoService {

    private final TipoProdutoRepository repository;

    public TipoProduto porId(int id){
        return repository.findById(id).orElseThrow(()-> new NaoEncontradoException("Produto"));
    }
}
