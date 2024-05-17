package school.sptech.backend.api.campanha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.service.campanha.CampanhaService;
import school.sptech.backend.service.campanha.dto.CampanhaAtualizacaoDto;
import school.sptech.backend.service.campanha.dto.CampanhaCriacaoDto;
import school.sptech.backend.service.campanha.dto.CampanhaListagemDto;
import school.sptech.backend.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.backend.service.produto.dto.ProdutoListagemDto;

import java.util.List;

@RestController
@RequestMapping("/campanhas")
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody CampanhaCriacaoDto campanhaCriacao) {
        this.campanhaService.criar(campanhaCriacao);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<CampanhaListagemDto>> listar(){
        List<CampanhaListagemDto> dto = this.campanhaService.listar();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaListagemDto> porId(@PathVariable int id){
        CampanhaListagemDto dto = this.campanhaService.porId(id);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampanhaListagemDto> atualizar(@RequestBody CampanhaAtualizacaoDto campanhaAtualizado, @PathVariable int id) {
        CampanhaListagemDto dto = this.campanhaService.atualizar(campanhaAtualizado, id);
        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        this.campanhaService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
