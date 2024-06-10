package school.sptech.backend.service.produtounitario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.domain.produtounitario.repository.ProdutoUnitarioRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.cesta.CestaService;
import school.sptech.backend.service.metrica.MetricaService;
import school.sptech.backend.service.origem.OrigemService;
import school.sptech.backend.service.produto.ProdutoService;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioArrecadadoXVencidoDto;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioCountMesDto;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioVencimento15E30DiasDto;
import school.sptech.backend.service.rota.RotaService;
import school.sptech.backend.service.unidademedida.UnidadeMedidaService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoUnitarioService {

    private final ProdutoUnitarioRepository repository;

    private final OrigemService origemService;
    private final UnidadeMedidaService unidadeMedidaService;
    private final CestaService cestaService;
    private final ProdutoService produtoService;
    private final RotaService rotaService;
    private final MetricaService metricaService;

    public ProdutoUnitario criar(ProdutoUnitario novoProdutoUnitario, Integer origemId, Integer unidadeMedidaId, Integer cestaId, Integer produtoId, Integer rotaId, Integer metricaId){
        novoProdutoUnitario.setOrigem(origemService.porId(origemId));
        novoProdutoUnitario.setUnidadeMedida(unidadeMedidaService.porId(unidadeMedidaId));
        novoProdutoUnitario.setCesta(cestaService.porId(cestaId));
        novoProdutoUnitario.setProduto(produtoService.porId(produtoId));
        novoProdutoUnitario.setRota(rotaService.porId(rotaId));
        novoProdutoUnitario.setMetrica(metricaService.porId(metricaId));
        novoProdutoUnitario.setDataCriacao(LocalDate.now());
        return repository.save(novoProdutoUnitario);
    }

    public List<ProdutoUnitario> listar(){
        return repository.findAll();
    }

    public ProdutoUnitario porId(Integer id){
        return repository.findById(id).orElseThrow(()-> new NaoEncontradoException("Produto Unitário"));
    }

    public List<ProdutoUnitario> buscaPorParteDoNome(String nome){
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public ProdutoUnitario atualizar(ProdutoUnitario produtoUnitarioAtualizado, Integer id){
        if (!repository.existsById(id)){
            throw new NaoEncontradoException("ProdutoUnitario");
        }
        produtoUnitarioAtualizado.setId(id);
        return repository.save(produtoUnitarioAtualizado);
    }

    public void deletar(int id){
        if (!repository.existsById(id)){
            throw new NaoEncontradoException("ProdutoUnitario");
        }
        repository.deleteById(id);
    }

    public List<ProdutoUnitarioCountMesDto> qtdAtivosPorMes(boolean ativo){
        return repository.qtdAtivosPorMes(ativo);
    }

    public ProdutoUnitarioVencimento15E30DiasDto alimentosVencimento15E30Dias(){
        return repository.alimentosVencimento15E30Dias();
    }
    public List<ProdutoUnitarioArrecadadoXVencidoDto> countAtivoByNome(){
        return repository.countAtivoByNome();
    }
}
