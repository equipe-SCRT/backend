package school.sptech.backendscrt.service.produtounitario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.backendscrt.domain.produtounitario.ProdutoUnitario;
import school.sptech.backendscrt.domain.produtounitario.repository.ProdutoUnitarioRepository;
import school.sptech.backendscrt.service.produtounitario.dto.ProdutoUnitarioAtualizacaoDto;
import school.sptech.backendscrt.service.produtounitario.dto.ProdutoUnitarioCriacaoDto;
import school.sptech.backendscrt.service.produtounitario.dto.ProdutoUnitarioListagemDto;
import school.sptech.backendscrt.service.produtounitario.dto.ProdutoUnitarioMapper;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoUnitarioService {
    @Autowired
    private ProdutoUnitarioRepository produtoUnitarioRepository;

    public List<ProdutoUnitarioListagemDto> listar() {
        final List<ProdutoUnitario> produtos = this.produtoUnitarioRepository.findAll();
        return ProdutoUnitarioMapper.toDto(produtos);
    }

    public void adicionar(ProdutoUnitarioCriacaoDto produtoUnitarioCriacaoDto) {
        final ProdutoUnitario novoProdutoUnitario = ProdutoUnitarioMapper.toEntity(produtoUnitarioCriacaoDto);
        this.produtoUnitarioRepository.save(novoProdutoUnitario);
    }

    public void atualizar(int id, ProdutoUnitarioAtualizacaoDto produtoUnitarioAtualizacaoDto) {
        Optional<ProdutoUnitario> produtoUnitarioOpt = produtoUnitarioRepository.findById(id);

        produtoUnitarioOpt.get().setId(id);
        produtoUnitarioOpt.get().setNome(produtoUnitarioAtualizacaoDto.getNome());
        produtoUnitarioOpt.get().setDataValidade(produtoUnitarioAtualizacaoDto.getDataValidade());
        produtoUnitarioOpt.get().setPeso(produtoUnitarioAtualizacaoDto.getPeso());
        produtoUnitarioOpt.get().setFkOrigem(produtoUnitarioAtualizacaoDto.getFkOrigem());
        produtoUnitarioOpt.get().setFkUnidadeMedida(produtoUnitarioAtualizacaoDto.getFkUnidadeMedida());
        produtoUnitarioOpt.get().setFkCesta(produtoUnitarioAtualizacaoDto.getFkCesta());
        produtoUnitarioOpt.get().setFkProduto(produtoUnitarioAtualizacaoDto.getFkProduto());
        produtoUnitarioOpt.get().setFkRota(produtoUnitarioAtualizacaoDto.getFkRota());

        ProdutoUnitario produtoUnitarioSalvo = produtoUnitarioRepository.save(produtoUnitarioOpt.get());
        ProdutoUnitarioMapper.toDto(produtoUnitarioSalvo);
    }

    public void deletar(int id) {
        Optional<ProdutoUnitario> produtoUnitarioOpt = this.produtoUnitarioRepository.findById(id);
        this.produtoUnitarioRepository.delete(produtoUnitarioOpt.get());
    }
}
