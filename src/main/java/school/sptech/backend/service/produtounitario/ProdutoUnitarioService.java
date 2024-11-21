package school.sptech.backend.service.produtounitario;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.origem.Origem;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.domain.produtounitario.repository.ProdutoUnitarioRepository;
import school.sptech.backend.domain.produtounitario.repository.ProdutosConformeNaoConformeCampanhasRepository;
import school.sptech.backend.domain.produtounitario.repository.QtdProdutoPorCampanhaRepository;
import school.sptech.backend.domain.produtounitario.repository.QtdProdutosVencidosPorCampanhaRepository;
import school.sptech.backend.domain.produtounitario.repository.*;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.BaseService;
import school.sptech.backend.service.metrica.MetricaService;
import school.sptech.backend.service.origem.OrigemService;
import school.sptech.backend.service.produto.ProdutoService;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioListagemDto;
import school.sptech.backend.service.produtounitario.dto.QtdProdutoPorCampanhaListagemDto;
import school.sptech.backend.service.produtounitario.view.ProdutosConformeNaoConformeCampanhas;
import school.sptech.backend.service.produtounitario.view.QtdProdutoPorCampanha;
import school.sptech.backend.service.produtounitario.view.QtdProdutosVencidosPorCampanha;
import school.sptech.backend.service.rota.RotaService;
import school.sptech.backend.service.produtounitario.view.QtdAtivoPorMes;
import school.sptech.backend.service.produtounitario.view.QtdVencidoPorMes;
import school.sptech.backend.service.produtounitario.view.VencidoArrecadado;
import school.sptech.backend.service.produtounitario.view.Vencimento15E30Dias;
import school.sptech.backend.service.unidademedida.UnidadeMedidaService;

import java.util.ArrayList;
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
    private final RotaService rotaService;
    private final MetricaService metricaService;
    private final QtdProdutoPorCampanhaRepository qtdProdutoPorCampanhaRepository;
    private final QtdProdutosVencidosPorCampanhaRepository qtdProdutosVencidosPorCampanhaRepository;
    private final ProdutosConformeNaoConformeCampanhasRepository produtosConformeNaoConformeCampanhasRepository;
  
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

    public List<QtdProdutoPorCampanha> qtdProdutoPorCampanha(Integer id){
        return qtdProdutoPorCampanhaRepository.findByProdutoId(id);
    }

    public List<QtdProdutosVencidosPorCampanha> qtdProdutosVencidosPorCampanha(Integer id){
        return qtdProdutosVencidosPorCampanhaRepository.findByProdutoId(id);
    }

    public List<ProdutosConformeNaoConformeCampanhas> produtosConformeNaoConformeCampanhas(){
        return produtosConformeNaoConformeCampanhasRepository.findTop4ByOrderByNaoConformeDesc();
    }

    public List<ProdutoUnitarioListagemDto> dtoComOrigem(List<ProdutoUnitarioListagemDto> dtos) {
        List<ProdutoUnitarioListagemDto> produtoUnitarioListagemDtos = new ArrayList<>();
        for (ProdutoUnitarioListagemDto dto : dtos) {
            Origem origem = origemService.porId(dto.getOrigem().getId());
            dto.getOrigem().setItapora(origem.getItapora());
            dto.getOrigem().setCampanha(origem.getCampanha());
            dto.getOrigem().setAutaDeSouzaRua(origem.getAutaDeSouzaRua());
            dto.getOrigem().setCondominio(origem.getCondominio());


            produtoUnitarioListagemDtos.add(dto);
        }
        return produtoUnitarioListagemDtos;
    }

    public Page<ProdutoUnitario> listarPaginado(Integer paginaAtual, Integer tamanho) {
        Sort sort = Sort.by(Sort.Order.asc("Id"));
        PageRequest pageable = PageRequest.of(paginaAtual, tamanho, sort);
        Page<ProdutoUnitario> page = this.repository.findAll(pageable);


        return page;
    }
}
