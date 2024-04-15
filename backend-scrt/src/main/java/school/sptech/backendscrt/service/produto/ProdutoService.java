package school.sptech.backendscrt.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.backendscrt.domain.produto.Produto;
import school.sptech.backendscrt.domain.produto.repository.ProdutoRepository;
import school.sptech.backendscrt.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.backendscrt.service.produto.dto.ProdutoCriacaoDto;
import school.sptech.backendscrt.service.produto.dto.ProdutoListagemDto;
import school.sptech.backendscrt.service.produto.dto.ProdutoMapper;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoListagemDto> listar() {
        final List<Produto> produtos = this.produtoRepository.findAll();
        return ProdutoMapper.toDto(produtos);
    }

    public void adicionar(ProdutoCriacaoDto produtoCriacaoDto) {
        final Produto novoProduto = ProdutoMapper.toEntity(produtoCriacaoDto);
        this.produtoRepository.save(novoProduto);
    }

    public void atualizar(int id, ProdutoAtualizacaoDto produtoAtualizacaoDto) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        produtoOpt.get().setId(id);
        produtoOpt.get().setNome(produtoAtualizacaoDto.getNome());
        produtoOpt.get().setFkTipoProduto(produtoAtualizacaoDto.getFkTipoProduto());

        Produto produtoSalvo = produtoRepository.save(produtoOpt.get());
        ProdutoMapper.toDto(produtoSalvo);
    }

    public void deletar(int id) {
        Optional<Produto> produtoOpt = this.produtoRepository.findById(id);
        this.produtoRepository.delete(produtoOpt.get());
    }
}
