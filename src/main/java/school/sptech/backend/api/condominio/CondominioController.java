package school.sptech.backend.api.condominio;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.api.BaseController;
import school.sptech.backend.domain.condominio.Condominio;
import school.sptech.backend.service.condominio.CondominioService;
import school.sptech.backend.service.condominio.dto.*;
import school.sptech.backend.service.condominio.view.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/condominios")
@RequiredArgsConstructor
public class CondominioController implements BaseController<CondominioCriacaoDto,CondominioAtualizacaoDto,CondominioListagemDto,Integer> {

    private final CondominioService service;
    private final CondominioMapper mapper;
    private final QtdTotalArrecadadaMapper qtdTotalArrecadadaMapper;
    private final QtdProdutosVencidosMapper qtdProdutosVencidosMapper;
    private final QtdProdutosNaoConformeMapper qtdProdutosNaoConformeMapper;
    private final ProdutosConformeENaoConformeMapper produtosConformeENaoConformeMapper;
    private final ProdutosArrecadadosPorMesMapper produtosArrecadadosPorMesMapper;
    private final ProdutosArrecadadosPorCondominioMapper produtosArrecadadosPorCondominioMapper;
    private final QtdProdutosPorNomeCondominioMapper qtdProdutosPorNomeCondominioMapper;

    @PostMapping
    public ResponseEntity<CondominioListagemDto> criar(@RequestBody @Valid CondominioCriacaoDto dto) {
        Condominio condominioCriado = mapper.toEntity(dto);
        Condominio resposta = this.service.criar(condominioCriado);
        URI uri = URI.create("/condominios/" + resposta.getId());
        return ResponseEntity.created(uri).body(mapper.toDto(resposta));
    }

    @GetMapping
    public ResponseEntity<List<CondominioListagemDto>> listar() {
        List<Condominio> condominios = this.service.listar();
        return ResponseEntity.ok().body(mapper.toDto(condominios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondominioListagemDto> porId(@PathVariable Integer id) {
        Condominio entity = this.service.porId(id);
        return ResponseEntity.ok().body(mapper.toDto(entity));
    }

    @GetMapping("/por-nome/{nome}")
    public ResponseEntity<CondominioListagemDto> porNome(@PathVariable String nome) {
        Condominio entity = this.service.porNome(nome);
        return ResponseEntity.ok().body(mapper.toDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondominioListagemDto> atualizar(@PathVariable Integer id, @RequestBody @Valid CondominioAtualizacaoDto condominioAtualizado) {
        Condominio entity = mapper.toEntity(condominioAtualizado);
        CondominioListagemDto dto = mapper.toDto(this.service.atualizar(entity.getId(), entity));
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        this.service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/qtd-total-arrecadada/{id}")
    public ResponseEntity<List<QtdTotalArrecadadaListagemDto>> qtdTotalArrecadada(@PathVariable Integer id) {
        List<QtdTotalArrecadada> qtdTotalArrecadada = service.listarQtdArrecadada(id);
        List<QtdTotalArrecadadaListagemDto> dto = qtdTotalArrecadadaMapper.toDto(qtdTotalArrecadada);
        dto.forEach(System.out::println);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/qtd-produtos-vencidos/{id}")
    public ResponseEntity<List<QtdProdutosVencidosListagemDto>> qtdProdutosVencidos(@PathVariable Integer id) {
        List<QtdProdutosVencidos> qtdProdutosVencidos = service.listarQtdVencidos(id);
        List<QtdProdutosVencidosListagemDto> dto = qtdProdutosVencidosMapper.toDto(qtdProdutosVencidos);
        dto.forEach(System.out::println);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/qtd-produtos-nao-conforme/{id}")
    public ResponseEntity<List<QtdProdutosNaoConformeListagemDto>> qtdProdutosNaoConforme(@PathVariable Integer id) {
        List<QtdProdutosNaoConforme> qtdProdutosNaoConforme = service.listarQtdNaoConforme(id);
        List<QtdProdutosNaoConformeListagemDto> dto = qtdProdutosNaoConformeMapper.toDto(qtdProdutosNaoConforme);
        dto.forEach(System.out::println);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/produtos-conforme-e-nao-conforme")
    public ResponseEntity<List<ProdutosConformeENaoConformeListagemDto>> produtosConformeENaoConforme() {
        List<ProdutosConformeENaoConforme> produtosConformeENaoConforme = service.listarProdutosConformeENaoConforme();
        List<ProdutosConformeENaoConformeListagemDto> dto = produtosConformeENaoConformeMapper.toDto(produtosConformeENaoConforme);
        dto.forEach(System.out::println);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/produtos-arrecadados-por-mes")
    public ResponseEntity<List<ProdutosArrecadadosPorMesListagemDto>> produtosArrecadadosPorMes() {
        List<ProdutosArrecadadosPorMes> produtosArrecadadosPorMes = service.listarProdutosArrecadadosPorMes();
        List<ProdutosArrecadadosPorMesListagemDto> dto = produtosArrecadadosPorMesMapper.toDto(produtosArrecadadosPorMes);
        dto.forEach(System.out::println);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/produtos-arrecadados-por-condominio/{id}")
    public ResponseEntity<List<ProdutosArrecadadosPorCondominioListagemDto>> produtosArrecadadosPorCondominio(@PathVariable Integer id) {
        List<ProdutosArrecadadosPorCondominio> produtosArrecadadosPorCondominio = service.listarProdutosArrecadadosPorCondominio(id);
        List<ProdutosArrecadadosPorCondominioListagemDto> dto = produtosArrecadadosPorCondominioMapper.toDto(produtosArrecadadosPorCondominio);
        dto.forEach(System.out::println);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/produtos-por-nome-condominio/{nomeCondominio}")
    public ResponseEntity<List<QtdProdutosPorNomeCondominioListagemDto>> produtosPorNomeCondominio(@PathVariable String nomeCondominio) {
        List<QtdProdutosPorNomeCondominio> produtosPorNomeCondominio = service.listarProdutosPorNomeCondominio(nomeCondominio);
        List<QtdProdutosPorNomeCondominioListagemDto> dto = qtdProdutosPorNomeCondominioMapper.toDto(produtosPorNomeCondominio);
        dto.forEach(System.out::println);
        return ResponseEntity.ok(dto);
    }


}