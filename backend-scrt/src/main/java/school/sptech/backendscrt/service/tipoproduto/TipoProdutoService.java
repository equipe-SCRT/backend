package school.sptech.backendscrt.service.tipoproduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.backendscrt.domain.tipoProduto.TipoProduto;
import school.sptech.backendscrt.domain.tipoProduto.repository.TipoProdutoRepository;
import school.sptech.backendscrt.service.tipoproduto.dto.TipoProdutoAtualizacaoDto;
import school.sptech.backendscrt.service.tipoproduto.dto.TipoProdutoCriacaoDto;
import school.sptech.backendscrt.service.tipoproduto.dto.TipoProdutoListagemDto;
import school.sptech.backendscrt.service.tipoproduto.dto.TipoProdutoMapper;

import java.util.List;
import java.util.Optional;

@Service
public class TipoProdutoService {
    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    public void criar(TipoProdutoCriacaoDto tipoProdutoCriacao){
        final TipoProduto novoTipoProduto = TipoProdutoMapper.toEntity(tipoProdutoCriacao);
        this.tipoProdutoRepository.save(novoTipoProduto);
    }

    public List<TipoProdutoListagemDto> listar(){
        final List<TipoProduto> tipoProdutos = this.tipoProdutoRepository.findAll();

        if (tipoProdutos.isEmpty()){
            return null;
        }

        final List<TipoProdutoListagemDto> dto = TipoProdutoMapper.toDto(tipoProdutos);

        return dto;
    }

    public TipoProdutoListagemDto porId(int id){
        final Optional<TipoProduto> tipoProdutoOpt = this.tipoProdutoRepository.findById(id);

        if (tipoProdutoOpt.isEmpty()){
            return null;
        }

        final TipoProdutoListagemDto dto = TipoProdutoMapper.toDto(tipoProdutoOpt.get());

        return dto;
    }

    public TipoProdutoListagemDto atualizar(TipoProdutoAtualizacaoDto tipoProdutoAtualizado, int id){
        final Optional<TipoProduto> tipoProdutoOpt = this.tipoProdutoRepository.findById(id);

        if (tipoProdutoOpt.isEmpty()){
            return null;
        }

        final TipoProduto tipoProduto = TipoProdutoMapper.atualizacaoDto(tipoProdutoOpt.get(), tipoProdutoAtualizado);

        final TipoProdutoListagemDto dto = TipoProdutoMapper.toDto(tipoProduto);

        this.tipoProdutoRepository.save(tipoProduto);

        return dto;
    }

    public void deletar(int id){
        final Optional<TipoProduto> tipoProdutoOpt = this.tipoProdutoRepository.findById(id);

        if (tipoProdutoOpt.isPresent()){
            this.tipoProdutoRepository.deleteById(id);
        }
    }
}
