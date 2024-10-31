package school.sptech.backend.api.produtounitario;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.api.BaseController;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.service.produtounitario.ProdutoUnitarioService;
import school.sptech.backend.service.produtounitario.dto.*;


import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos-unitario")
@RequiredArgsConstructor
public class ProdutoUnitarioController implements BaseController<ProdutoUnitarioCriacaoDto, ProdutoUnitarioAtualizacaoDto, ProdutoUnitarioListagemDto, Integer> {

    private final ProdutoUnitarioService service;

    private final ProdutoUnitarioMapper mapper;

    @PostMapping
    public ResponseEntity<ProdutoUnitarioListagemDto> criar(@RequestBody @Valid ProdutoUnitarioCriacaoDto novoProdutoUnitario){
        ProdutoUnitario produtoUnitarioCriado = mapper.toEntity(novoProdutoUnitario);
        ProdutoUnitario resposta = service.criar(produtoUnitarioCriado);

        URI uri = URI.create("/produtos-unitario/" + resposta.getId());

        return ResponseEntity.created(uri).body(mapper.toDto(resposta));
    }

    @PostMapping("/lotes")
    public ResponseEntity<List<ProdutoUnitarioListagemDto>> criarEmLote(@RequestBody @Valid List<ProdutoUnitarioCriacaoDto> novoProdutoUnitario){
        List<ProdutoUnitarioListagemDto> lista = new ArrayList<>();
        for (int i = 0; i < novoProdutoUnitario.size(); i++) {
            ProdutoUnitario produtoUnitarioCriado = mapper.toEntity(novoProdutoUnitario.get(i));
            ProdutoUnitario resposta = service.criar(produtoUnitarioCriado);

            lista.add(mapper.toDto(resposta));
        }
        return ResponseEntity.status(201).body(lista);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoUnitarioListagemDto>> listar(){
        List<ProdutoUnitario> produtoUnitarios = service.listar();

        if (produtoUnitarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ProdutoUnitarioListagemDto> dtos = mapper.toDto(produtoUnitarios);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoUnitarioListagemDto> porId(@PathVariable Integer id){
        ProdutoUnitario produtoUnitario = service.porId(id);
        ProdutoUnitarioListagemDto dto = mapper.toDto(produtoUnitario);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<ProdutoUnitarioListagemDto>> buscarPorParteDoNome(@RequestParam String nome){
        List<ProdutoUnitario> produtoUnitarios = service.buscaPorParteDoNome(nome);

        if (produtoUnitarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ProdutoUnitarioListagemDto> dtos = mapper.toDto(produtoUnitarios);

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoUnitarioListagemDto> atualizar(
            @PathVariable Integer id, @RequestBody @Valid ProdutoUnitarioAtualizacaoDto produtoUnitarioAtualizado
    ) {
        ProdutoUnitario produtoUnitario = mapper.toEntity(produtoUnitarioAtualizado);
        ProdutoUnitario resposta = service.atualizar(produtoUnitario.getId(), produtoUnitario);
        ProdutoUnitarioListagemDto dto = mapper.toDto(resposta);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/lotes-delete")
    public ResponseEntity<Void> deletar(@RequestBody List<ProdutoUnitarioListagemDto> listaProdutos){
        for (ProdutoUnitarioListagemDto listaProduto : listaProdutos) {
            service.deletar(listaProduto.getId());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/quantidade-produtos/mes")
    public ResponseEntity<List<QtdAtivoPorMesDto>> qtdAtivosPorMes(
            @PathVariable Integer id, @RequestParam LocalDate inicio, @RequestParam LocalDate fim
    ){
        List<QtdAtivoPorMesDto> dto = mapper.qtdAtivoPorMesToDto(service.qtdAtivoPorMesPorIdDataEntre(id, inicio, fim));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}/quantidade-produtos/mes/vencidos")
    public ResponseEntity<List<QtdVencidoPorMesDto>> qtdVencidosPorMes(
            @PathVariable Integer id, @RequestParam LocalDate inicio, @RequestParam LocalDate fim
    ){
        List<QtdVencidoPorMesDto> dto = mapper.qtdVencidoPorMesToDto(service.qtdVencidoPorMesPorIdDataEntre(id, inicio, fim));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/vencimento-em-15-e-30-dias")
    public ResponseEntity<Vencimento15E30DiasDto> alimentosVencimento15E30Dias(){
        return ResponseEntity.ok(mapper.vencimento15E30DiasToDto(service.vencimento15E30Dias()));
    }

    @GetMapping("/arrecadados-vencidos")
    public ResponseEntity<List<VencidoArrecadadoDto>> arrecadadosVencidos(){
        return ResponseEntity.ok(mapper.vencidoArrecadadoToDto(service.arrecadadosVencidos()));
    }
    @GetMapping("/total-estoque")
    public ResponseEntity<Integer> totalEstoque(){
        return ResponseEntity.ok(service.totalEstoque());
    }

    @GetMapping("/total-vencidos")
    public ResponseEntity<Integer> totalVencidos(@RequestParam LocalDate inicio, @RequestParam LocalDate fim){
        return ResponseEntity.ok(service.totalVencidos(inicio, fim));
    }

    @GetMapping("/data-vencimento")
    public ResponseEntity<List<ProdutoUnitarioListagemDto>> listarPorDataMaiorQue(@RequestParam LocalDate inicio, @RequestParam LocalDate fim){
        return ResponseEntity.ok(mapper.toDto(service.listarPorDataEntre(inicio, fim)));
    }
    @GetMapping("/data-vencimento/menor-que")
    public ResponseEntity<List<ProdutoUnitarioListagemDto>> listarPorDataMenorQue(@RequestParam LocalDate data){
        return ResponseEntity.ok(mapper.toDto(service.listarPorDataMenorQue(data)));
    }
}
