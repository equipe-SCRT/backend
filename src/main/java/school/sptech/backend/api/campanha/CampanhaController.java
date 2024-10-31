package school.sptech.backend.api.campanha;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.api.BaseController;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.service.campanha.CampanhaService;
import school.sptech.backend.service.campanha.dto.*;
import school.sptech.backend.service.campanha.view.QtdDoacoesPorCampanha;
import school.sptech.backend.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.backend.service.produto.dto.ProdutoListagemDto;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/campanhas")
@RequiredArgsConstructor
public class CampanhaController implements BaseController<CampanhaCriacaoDto,CampanhaAtualizacaoDto, CampanhaListagemDto, Integer> {

    private final CampanhaService campanhaService;
    private final CampanhaMapper campanhaMapper;
    private final QtdDoacoesPorCampanhaMapper qtdDoacoesPorCampanhaMapper;

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
    public ResponseEntity<CampanhaListagemDto> porId(@PathVariable Integer id){
        Campanha entity = this.campanhaService.porId(id);
        return ResponseEntity.ok().body(campanhaMapper.toDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampanhaListagemDto> atualizar(@PathVariable Integer id, @RequestBody @Valid CampanhaAtualizacaoDto campanhaAtualizado) {
        Campanha entity = campanhaMapper.toEntity(campanhaAtualizado);
        CampanhaListagemDto dto = campanhaMapper.toDto(this.campanhaService.atualizar(entity.getId(), entity));
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        this.campanhaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/doacoes-por-campanhas")
    public ResponseEntity<List<QtdDoacoesPorCampanhaListagemDto>> qtdDoacoesPorCampanha(@RequestParam String nome){
        List<QtdDoacoesPorCampanha> qtdProdutosVencidosPorCampanha = campanhaService.qtdDoacoesPorCampanhas(nome);
        List<QtdDoacoesPorCampanhaListagemDto> dto = qtdDoacoesPorCampanhaMapper.toDto(qtdProdutosVencidosPorCampanha);
        return ResponseEntity.ok(dto);
    }


    @GetMapping("/dados-por-data")
    public ResponseEntity<List<CampanhaListagemDto>> porData(@RequestParam LocalDate data){
        List<Campanha> campanhas = campanhaService.porData(data);
        List<CampanhaListagemDto> dto = campanhaMapper.toDto(campanhas);
        return ResponseEntity.ok(dto);
    }
}
