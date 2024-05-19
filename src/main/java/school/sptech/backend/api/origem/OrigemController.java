package school.sptech.backend.api.origem;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.domain.origem.Origem;
import school.sptech.backend.service.origem.OrigemService;
import school.sptech.backend.service.origem.dto.OrigemAtualizacaoDto;
import school.sptech.backend.service.origem.dto.OrigemCriacaoDto;
import school.sptech.backend.service.origem.dto.OrigemListagemDto;
import school.sptech.backend.service.origem.dto.OrigemMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/origens")
@RequiredArgsConstructor
public class OrigemController {

    private final OrigemService origemService;
    private final OrigemMapper origemMapper;

    @PostMapping
    public ResponseEntity<OrigemListagemDto> criar(@RequestBody @Valid OrigemCriacaoDto origemCriacao) {
        Origem origemCriada = this.origemService.criar(origemMapper.toEntity(origemCriacao));
        URI uri = URI.create("/origens/" + origemCriada.getId());
        return ResponseEntity.created(uri).body(origemMapper.toDto(origemCriada));
    }

    @GetMapping
    public ResponseEntity<List<OrigemListagemDto>> listar(){
        List<Origem> origens = this.origemService.listar();
        return ResponseEntity.ok().body(origemMapper.toDto(origens));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrigemListagemDto> porId(@PathVariable int id){
        Origem entity = this.origemService.porId(id);
        return ResponseEntity.ok().body(origemMapper.toDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrigemListagemDto> atualizar(@RequestBody @Valid OrigemAtualizacaoDto origemAtualizacao, @PathVariable int id) {
        Origem entity = origemMapper.atualizacaoDto(origemAtualizacao, id);
        OrigemListagemDto dto = origemMapper.toDto(this.origemService.atualizar(entity, entity.getId()));
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        this.origemService.deletar(id);
        return ResponseEntity.noContent().build();
    }




}
