package school.sptech.backend.api.produto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.api.BaseController;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.service.produto.ProdutoService;
import school.sptech.backend.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.backend.service.produto.dto.ProdutoCriacaoDto;
import school.sptech.backend.service.produto.dto.ProdutoListagemDto;
import school.sptech.backend.service.produto.dto.ProdutoMapper;
import school.sptech.backend.view.alimentosarrecadadospormes.AlimentosArrecadadosPorMes;
import school.sptech.backend.view.alimentosarrecadadospormes.dto.AlimentosArrecadadosPorMesListagemDto;
import school.sptech.backend.view.alimentosarrecadadospormes.dto.AlimentosArrecadadosPorMesMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController implements BaseController<ProdutoCriacaoDto, ProdutoAtualizacaoDto, ProdutoListagemDto, Integer> {

    private final ProdutoService service;

    private final ProdutoMapper mapper;

    private final AlimentosArrecadadosPorMesMapper alimentosArrecadadosPorMesMapper;

    @PostMapping
    public ResponseEntity<ProdutoListagemDto> criar(@RequestBody @Valid ProdutoCriacaoDto novoProduto){
        Produto produtoCriado = mapper.toEntity(novoProduto);
        Produto resposta = service.criar(produtoCriado);
        ProdutoListagemDto dto = mapper.toDto(resposta);

        URI uri = URI.create("/produtos/" + dto.getId());

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoListagemDto>> listar(){
        List<Produto> produtos = service.listar();

        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ProdutoListagemDto> dtos = mapper.toDto(produtos);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoListagemDto> porId(@PathVariable Integer id){
        Produto produto = service.porId(id);
        ProdutoListagemDto dto = mapper.toDto(produto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<ProdutoListagemDto>> buscaPorParteDoNome(@RequestParam String nome){
        List<Produto> produtos = service.buscaPorParteDoNome(nome);

        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ProdutoListagemDto> dtos = mapper.toDto(produtos);

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoListagemDto> atualizar(@PathVariable Integer id, @RequestBody @Valid ProdutoAtualizacaoDto produtoAtualizado) {
        Produto produto = mapper.atualizacaoDto(produtoAtualizado);
        Produto resposta = service.atualizar(produto.getId(), produto);
        ProdutoListagemDto dto = mapper.toDto(resposta);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/alimentos-arrecadados-por-mes")
    public ResponseEntity<List<AlimentosArrecadadosPorMesListagemDto>> alimentosArrecadadosPorMes(){
        List<AlimentosArrecadadosPorMes> alimentosArrecadadosPorMes = service.alimentosArrecadadosPorMes();
        List<AlimentosArrecadadosPorMesListagemDto> dto = alimentosArrecadadosPorMesMapper.toDto(alimentosArrecadadosPorMes);
        dto.forEach(System.out::println);
        return ResponseEntity.ok(dto);
    }

}
