package school.sptech.backend.service.produtounitario;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.domain.produtounitario.repository.ProdutoUnitarioRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.BaseService;
import school.sptech.backend.service.cesta.CestaService;
import school.sptech.backend.service.metrica.MetricaService;
import school.sptech.backend.service.origem.OrigemService;
import school.sptech.backend.service.produto.ProdutoService;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioArrecadadoXVencidoDto;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioCountMesDto;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioVencimento15E30DiasDto;
import school.sptech.backend.service.rota.RotaService;
import school.sptech.backend.service.unidademedida.UnidadeMedidaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoUnitarioService implements BaseService<ProdutoUnitario, Integer>{

    private final ProdutoUnitarioRepository repository;

    private final OrigemService origemService;
    private final UnidadeMedidaService unidadeMedidaService;
    private final CestaService cestaService;
    private final ProdutoService produtoService;
    private final RotaService rotaService;
    private final MetricaService metricaService;

    public ProdutoUnitario criar(ProdutoUnitario novoProdutoUnitario){
        novoProdutoUnitario.setOrigem(origemService.porId(novoProdutoUnitario.getOrigem().getId()));
        novoProdutoUnitario.setUnidadeMedida(unidadeMedidaService.porId(novoProdutoUnitario.getUnidadeMedida().getId()));
        novoProdutoUnitario.setCesta(cestaService.porId(novoProdutoUnitario.getCesta().getId()));
        novoProdutoUnitario.setProduto(produtoService.porId(novoProdutoUnitario.getProduto().getId()));
        novoProdutoUnitario.setRota(rotaService.porId(novoProdutoUnitario.getRota().getId()));
        novoProdutoUnitario.setMetrica(metricaService.porId(novoProdutoUnitario.getMetrica().getId()));
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

    public ProdutoUnitario atualizar(Integer id, ProdutoUnitario produtoUnitarioAtualizado){
        if (!repository.existsById(id)){
            throw new NaoEncontradoException("ProdutoUnitario");
        }
        produtoUnitarioAtualizado.setId(id);
        return repository.save(produtoUnitarioAtualizado);
    }

    public Void deletar(Integer id){
        if (!repository.existsById(id)){
            throw new NaoEncontradoException("ProdutoUnitario");
        }
        repository.deleteById(id);
        return null;
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

    @Scheduled(cron = "0 0 * * * *")
    @EventListener(ApplicationReadyEvent.class)
    public void verificarProdutosForaDaValidade(){
        repository.verificarProdutosForaDaValidade();
    }

}
