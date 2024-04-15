package school.sptech.backendscrt.api.campanha;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.domain.campanha.Campanha;
import school.sptech.backendscrt.service.campanha.CampanhaService;
import school.sptech.backendscrt.service.campanha.dto.CampanhaAtualizacaoDto;
import school.sptech.backendscrt.service.campanha.dto.CampanhaCriacaoDto;
import school.sptech.backendscrt.service.campanha.dto.CampanhaListagemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/campanhas")
@Tag(name = "Campanha")
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    List<CampanhaListagemDto> campanhas = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<CampanhaListagemDto>> listar() {
        this.campanhaService.listar();
        return ResponseEntity.status(200).build();
    }

    @PostMapping
//    @SecurityRequirement(name = "") todo tofix
    public ResponseEntity<Campanha> adicionar(@RequestBody @Valid CampanhaCriacaoDto campanhaCriacaoDto) {
        if (Objects.isNull(campanhaCriacaoDto)) {
            return ResponseEntity.status(400).build();
        }
        this.campanhaService.adicionar(campanhaCriacaoDto);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campanha> atualizar(@PathVariable int id, @RequestBody @Valid CampanhaAtualizacaoDto campanhaAtualizacaoDto) {
        if (isInList(id)) {
            this.campanhaService.atualizar(id, campanhaAtualizacaoDto);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        if (isInList(id)) {
            this.campanhaService.deletar(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int id){
        return id > 0 && id < campanhas.size();
    }
}
