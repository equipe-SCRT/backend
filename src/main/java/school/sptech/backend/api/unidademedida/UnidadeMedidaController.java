package school.sptech.backend.api.unidademedida;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.domain.unidademedida.UnidadeMedida;
import school.sptech.backend.service.unidademedida.UnidadeMedidaService;
import school.sptech.backend.service.unidademedida.dto.UnidadeMedidaAtualizacaoDto;
import school.sptech.backend.service.unidademedida.dto.UnidadeMedidaCriacaoDto;
import school.sptech.backend.service.unidademedida.dto.UnidadeMedidaListagemDto;
import school.sptech.backend.service.unidademedida.dto.UnidadeMedidaMapper;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/unidades-medidas")
@RequiredArgsConstructor
public class UnidadeMedidaController {

    private final UnidadeMedidaService service;

    private final UnidadeMedidaMapper mapper;

    @PostMapping
    public ResponseEntity<UnidadeMedidaListagemDto> criar(@RequestBody @Valid UnidadeMedidaCriacaoDto novaUnidadeMedida){
        UnidadeMedida unidadeMedidaCriada = mapper.toEntity(novaUnidadeMedida);
        UnidadeMedida resposta = service.criar(unidadeMedidaCriada);
        UnidadeMedidaListagemDto dto = mapper.toDto(resposta);

        URI uri = URI.create("/unidades-medidas/" + dto.getId());

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<UnidadeMedidaListagemDto>> listar(){
        List<UnidadeMedida> unidadeMedidas = service.listar();

        if (unidadeMedidas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<UnidadeMedidaListagemDto> dtos = mapper.toDto(unidadeMedidas);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeMedidaListagemDto> porId(@PathVariable Integer id){
        UnidadeMedida unidadeMedida = service.porId(id);
        UnidadeMedidaListagemDto dto = mapper.toDto(unidadeMedida);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeMedidaListagemDto> atualizar(@RequestBody @Valid UnidadeMedidaAtualizacaoDto unidadeMedidaAtualizada, @PathVariable Integer id) {
        UnidadeMedida unidadeMedida = mapper.atualizacaoDto(unidadeMedidaAtualizada, id);
        UnidadeMedida resposta = service.atualizar(unidadeMedida, unidadeMedida.getId());
        UnidadeMedidaListagemDto dto = mapper.toDto(resposta);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
