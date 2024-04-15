package school.sptech.backendscrt.api.produtounitario;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.domain.produtounitario.ProdutoUnitario;
import school.sptech.backendscrt.service.produtounitario.ProdutoUnitarioService;
import school.sptech.backendscrt.service.produtounitario.dto.ProdutoUnitarioAtualizacaoDto;
import school.sptech.backendscrt.service.produtounitario.dto.ProdutoUnitarioCriacaoDto;
import school.sptech.backendscrt.service.produtounitario.dto.ProdutoUnitarioListagemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/produtos-unitarios")
@Tag(name = "ProdutoUnitario")
public class ProdutoUnitarioController {

    @Autowired
    private ProdutoUnitarioService produtoUnitarioService;

    List<ProdutoUnitarioListagemDto> produtosUnitarios = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<ProdutoUnitarioListagemDto>> listar(){
        this.produtoUnitarioService.listar();
        return ResponseEntity.status(200).build();
    }

    public ResponseEntity<ProdutoUnitario> adicionar(@RequestBody @Valid ProdutoUnitarioCriacaoDto produtoUnitarioCriacaoDto) {
        if (Objects.isNull(produtoUnitarioCriacaoDto)){
            return ResponseEntity.status(400).build();
        }
        this.produtoUnitarioService.adicionar(produtoUnitarioCriacaoDto);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoUnitarioListagemDto> atualizar(@PathVariable int id, @RequestBody @Valid ProdutoUnitarioAtualizacaoDto produtoUnitarioAtualizacaoDto) {
        if (isInList(id)) {
            this.produtoUnitarioService.atualizar(id, produtoUnitarioAtualizacaoDto);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        if (isInList(id)){
            this.produtoUnitarioService.deletar(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int id){
        return id > 0 && id < produtosUnitarios.size();
    }
}
