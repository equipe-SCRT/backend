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
    public ResponseEntity<ProdutoUnitarioListagemDto> atualizar(@PathVariable Integer id, @RequestBody @Valid ProdutoUnitarioAtualizacaoDto produtoUnitarioAtualizado) {
        ProdutoUnitario produtoUnitario = mapper.atualizacaoDto(produtoUnitarioAtualizado);
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

    @GetMapping("/quantidade-produtos/mes")
    public ResponseEntity<List<ProdutoUnitarioCountMesDto>> qtdAtivosPorMes(){
        List<ProdutoUnitarioCountMesDto> dto = service.qtdAtivosPorMes();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/quantidade-produtos/mes/vencidos")
    public ResponseEntity<List<ProdutoUnitarioCountMesDto>> qtdVencidosPorMes(){
        List<ProdutoUnitarioCountMesDto> dto = service.qtdVencidosPorMes();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/vencimento-em-15-e-30-dias")
    public ResponseEntity<ProdutoUnitarioVencimento15E30DiasDto> alimentosVencimento15E30Dias(){
        return ResponseEntity.ok(service.alimentosVencimento15E30Dias());
    }

    @GetMapping("/arrecadados-x-vencidos")
    public ResponseEntity<List<ProdutoUnitarioArrecadadoXVencidoDto>> countAtivoByNome(){
        return ResponseEntity.ok(service.countAtivoByNome());
    }
}
