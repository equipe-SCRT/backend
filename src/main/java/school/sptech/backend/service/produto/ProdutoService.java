package school.sptech.backend.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produto.repository.ProdutoRepository;
import school.sptech.backend.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.backend.service.produto.dto.ProdutoCriacaoDto;
import school.sptech.backend.service.produto.dto.ProdutoListagemDto;
import school.sptech.backend.service.produto.dto.ProdutoMapper;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void criar(ProdutoCriacaoDto produtoCriacao){
        final Produto novoProduto = ProdutoMapper.toEntity(produtoCriacao);
        this.produtoRepository.save(novoProduto);
    }

    public List<ProdutoListagemDto> listar(){
        final List<Produto> produtos = this.produtoRepository.findAll();

        if (produtos.isEmpty()){
            return null;
        }

        final List<ProdutoListagemDto> dto = ProdutoMapper.toDto(produtos);

        return dto;
    }

    public ProdutoListagemDto porId(int id){
        final Optional<Produto> produtoOpt = this.produtoRepository.findById(id);

        if (produtoOpt.isEmpty()){
            return null;
        }

        final ProdutoListagemDto dto = ProdutoMapper.toDto(produtoOpt.get());

        return dto;
    }

    public ProdutoListagemDto atualizar(ProdutoAtualizacaoDto produtoAtualizado, int id){
        final Optional<Produto> produtoOpt = this.produtoRepository.findById(id);

        if (produtoOpt.isEmpty()){
            return null;
        }

        final Produto produto = ProdutoMapper.atualizacaoDto(produtoOpt.get(), produtoAtualizado);

        final ProdutoListagemDto dto = ProdutoMapper.toDto(produto);

        this.produtoRepository.save(produto);

        return dto;
    }

    public void deletar(int id){
        final Optional<Produto> produtoOpt = this.produtoRepository.findById(id);

        if (produtoOpt.isPresent()){
            this.produtoRepository.deleteById(id);
        }
    }
}
