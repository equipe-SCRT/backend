package school.sptech.backend.api.campanha;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.service.campanha.CampanhaService;
import school.sptech.backend.service.campanha.dto.CampanhaAtualizacaoDto;
import school.sptech.backend.service.campanha.dto.CampanhaCriacaoDto;
import school.sptech.backend.service.campanha.dto.CampanhaListagemDto;
import school.sptech.backend.service.campanha.dto.CampanhaMapper;
import school.sptech.backend.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.backend.service.produto.dto.ProdutoListagemDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/campanhas")
@RequiredArgsConstructor
public class CampanhaController {


    private final CampanhaService campanhaService;
    private final CampanhaMapper campanhaMapper;

    @PostMapping
    public ResponseEntity<CampanhaListagemDto> criar(@RequestBody @Valid CampanhaCriacaoDto campanhaCriacao) {
        Campanha campanhaCriada = this.campanhaService.criar(campanhaMapper.toEntity(campanhaCriacao));
        URI uri = URI.create("/campanhas/" + campanhaCriada.getId());
        return ResponseEntity.created(uri).body(campanhaMapper.toDto(campanhaCriada));
    }

    @GetMapping
    public ResponseEntity<List<CampanhaListagemDto>> listar(){
        List<Campanha> campanhas = this.campanhaService.listar();
        return ResponseEntity.ok().body(campanhaMapper.toDto(campanhas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaListagemDto> porId(@PathVariable int id){
        Campanha entity = this.campanhaService.porId(id);
        return ResponseEntity.ok().body(campanhaMapper.toDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampanhaListagemDto> atualizar(@RequestBody @Valid CampanhaAtualizacaoDto campanhaAtualizado, @PathVariable int id) {
        Campanha entity = campanhaMapper.atualizacaoDto(campanhaAtualizado, id);
        CampanhaListagemDto dto = campanhaMapper.toDto(this.campanhaService.atualizar(entity, entity.getId()));
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        this.campanhaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
