package school.sptech.backend.api.tipoproduto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.api.BaseController;
import school.sptech.backend.domain.tipoproduto.TipoProduto;
import school.sptech.backend.service.tipoproduto.TipoProdutoService;
import school.sptech.backend.service.tipoproduto.dto.TipoProdutoAtualizacaoDto;
import school.sptech.backend.service.tipoproduto.dto.TipoProdutoCriacaoDto;
import school.sptech.backend.service.tipoproduto.dto.TipoProdutoListagemDto;
import school.sptech.backend.service.tipoproduto.dto.TipoProdutoMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("tipos-produtos")
@RequiredArgsConstructor
public class TipoProdutoController implements BaseController<TipoProdutoCriacaoDto, TipoProdutoAtualizacaoDto, TipoProdutoListagemDto, Integer> {


    private final TipoProdutoService service;

    private final TipoProdutoMapper mapper;

    @PostMapping
    public ResponseEntity<TipoProdutoListagemDto> criar(@RequestBody @Valid TipoProdutoCriacaoDto novoTipoProduto){
        TipoProduto tipoProdutoCriado = mapper.toEntity(novoTipoProduto);
        TipoProduto resposta = service.criar(tipoProdutoCriado);
        TipoProdutoListagemDto dto = mapper.toDto(resposta);

        URI uri = URI.create("/tipos-produtos/" + dto.getId());

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<TipoProdutoListagemDto>> listar(){
        List<TipoProduto> tipoProdutos = service.listar();

        if (tipoProdutos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<TipoProdutoListagemDto> dtos = mapper.toDto(tipoProdutos);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProdutoListagemDto> porId(@PathVariable Integer id){
        TipoProduto tipoProduto = service.porId(id);
        TipoProdutoListagemDto dto = mapper.toDto(tipoProduto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/hash/{id}")
    public ResponseEntity<TipoProdutoListagemDto> porIdHash(@PathVariable Integer id){
        TipoProduto tipoProduto = service.porIdHash(id);
        TipoProdutoListagemDto dto = mapper.toDto(tipoProduto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoProdutoListagemDto> atualizar(@PathVariable Integer id,@Valid @RequestBody TipoProdutoAtualizacaoDto tipoProdutoAtualizado) {
        TipoProduto tipoProduto = mapper.toEntity(tipoProdutoAtualizado);
        TipoProduto resposta = service.atualizar(tipoProduto.getId(), tipoProduto);
        TipoProdutoListagemDto dto = mapper.toDto(resposta);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
