package school.sptech.backend.api.produtounitario;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.service.produtounitario.ProdutoUnitarioService;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioAtualizacaoDto;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioCriacaoDto;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioListagemDto;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioMapper;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos-unitario")
@RequiredArgsConstructor
public class ProdutoUnitarioController {

    private final ProdutoUnitarioService service;

    private final ProdutoUnitarioMapper mapper;

    @PostMapping
    public ResponseEntity<ProdutoUnitarioListagemDto> criar(@RequestBody @Valid ProdutoUnitarioCriacaoDto novoProdutoUnitario){
        ProdutoUnitario produtoUnitarioCriado = mapper.toEntity(novoProdutoUnitario);
        ProdutoUnitario resposta = service.criar(produtoUnitarioCriado, novoProdutoUnitario.getOrigemId(), novoProdutoUnitario.getUnidadeMedidaId(), novoProdutoUnitario.getCestaId(), novoProdutoUnitario.getProdutoId(), novoProdutoUnitario.getRotaId(), novoProdutoUnitario.getMetricaId());
        ProdutoUnitarioListagemDto dto = mapper.toDto(resposta);

        URI uri = URI.create("/produtos-unitario/" + dto.getId());

        return ResponseEntity.created(uri).body(dto);
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

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoUnitarioListagemDto> atualizar(@RequestBody @Valid ProdutoUnitarioAtualizacaoDto produtoUnitarioAtualizado, @PathVariable Integer id) {
        ProdutoUnitario produtoUnitario = mapper.atualizacaoDto(produtoUnitarioAtualizado, id);
        ProdutoUnitario resposta = service.atualizar(produtoUnitario, produtoUnitario.getId());
        ProdutoUnitarioListagemDto dto = mapper.toDto(resposta);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
