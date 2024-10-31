package school.sptech.backend.service.produtounitario;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.domain.produtounitario.repository.*;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.BaseService;
import school.sptech.backend.service.origem.OrigemService;
import school.sptech.backend.service.produto.ProdutoService;
import school.sptech.backend.service.produtounitario.view.QtdAtivoPorMes;
import school.sptech.backend.service.produtounitario.view.QtdVencidoPorMes;
import school.sptech.backend.service.produtounitario.view.VencidoArrecadado;
import school.sptech.backend.service.produtounitario.view.Vencimento15E30Dias;
import school.sptech.backend.service.unidademedida.UnidadeMedidaService;

import java.util.List;
import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProdutoUnitarioService implements BaseService<ProdutoUnitario, Integer>{

    private final ProdutoUnitarioRepository repository;
    private final VencidoArrecadadoRepository vencidoArrecadoRepository;
    private final QtdAtivoMesRepository qtdAtivoMesRepository;
    private final QtdVencidoMesRepository qtdVencidoMesRepository;
    private final Vencimento15E30DiasRepository vencimento15E30DiasRepository;


    private final OrigemService origemService;
    private final UnidadeMedidaService unidadeMedidaService;
    private final ProdutoService produtoService;


    public ProdutoUnitario criar(ProdutoUnitario novoProdutoUnitario){
        Produto produto = produtoService.porId(novoProdutoUnitario.getProduto().getId());
        novoProdutoUnitario.setOrigem(origemService.porId(novoProdutoUnitario.getOrigem().getId()));
        novoProdutoUnitario.setUnidadeMedida(unidadeMedidaService.porId(produto.getUnidadeMedida().getId()));
//        novoProdutoUnitario.setCesta(cestaService.porId(novoProdutoUnitario.getCesta().getId()));
//        novoProdutoUnitario.setProduto(produtoService.porId(novoProdutoUnitario.getProduto().getId()));
//        novoProdutoUnitario.setRota(rotaService.porId(novoProdutoUnitario.getRota().getId()));
        novoProdutoUnitario.setNome(produto.getNome());
//        novoProdutoUnitario.setMetrica(metricaService.porId(novoProdutoUnitario.getMetrica().getId()));

        return repository.save(novoProdutoUnitario);
    }

    public List<ProdutoUnitario> listar(){
        return repository.findAll();
    }
    public List<ProdutoUnitario> listarPorDataEntre(LocalDate inicio, LocalDate fim){
        return repository.findByDataValidadeBetween(inicio, fim);
    }

    public List<ProdutoUnitario> listarPorDataMenorQue(LocalDate data){
        return repository.findByDataValidadeBefore(data);
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
        Produto produto = produtoService.porId(produtoUnitarioAtualizado.getProduto().getId());

        produtoUnitarioAtualizado.setNome(produto.getNome());
        produtoUnitarioAtualizado.setProduto(produto);
        produtoUnitarioAtualizado.setId(id);
        produtoUnitarioAtualizado.setUnidadeMedida(unidadeMedidaService.porId(
                produto.getUnidadeMedida().getId()));

        return repository.save(produtoUnitarioAtualizado);
    }

    public Void deletar(Integer id){
        if (!repository.existsById(id)){
            throw new NaoEncontradoException("ProdutoUnitario");
        }
        repository.deleteById(id);
        return null;
    }

    public List<VencidoArrecadado> arrecadadosVencidos(){
        return vencidoArrecadoRepository.findAll();
    }

    public List<QtdAtivoPorMes> qtdAtivoPorMesPorIdDataEntre(Integer id, LocalDate inicio, LocalDate fim){
        return qtdAtivoMesRepository.findByProdutoIdAndCriadoEmBetween(id, inicio, fim);
    }

    public List<QtdVencidoPorMes> qtdVencidoPorMesPorIdDataEntre(Integer id, LocalDate inicio, LocalDate fim){
        return qtdVencidoMesRepository.findByProdutoIdAndDataValidadeBetween(id, inicio, fim);
    }

    public Vencimento15E30Dias vencimento15E30Dias(){
        return vencimento15E30DiasRepository.findAll().get(0);
    }

    public Integer totalEstoque(){
        return qtdAtivoMesRepository.sumByCriadoEmBetween();
    }

    public Integer totalVencidos(LocalDate inicio, LocalDate fim){
        return qtdVencidoMesRepository.sumByDataValidadeBetween(inicio, fim);
    }



    @Scheduled(cron = "0 0 0 * * *")
    @EventListener(ApplicationReadyEvent.class)
    public void verificarProdutosForaDaValidade(){
        repository.verificarProdutosForaDaValidade();
    }


}
