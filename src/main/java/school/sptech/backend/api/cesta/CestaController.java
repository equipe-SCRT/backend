package school.sptech.backend.api.cesta;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import school.sptech.backend.api.BaseController;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.service.cesta.CestaService;
import school.sptech.backend.service.cesta.dto.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cestas")
public class CestaController implements BaseController<CestaCriacaoDto, CestaAtualizacaoDto, CestaListagemDto, Integer> {
    private final CestaService cestaService;
    private final CestaMapper cestaMapper;

    @GetMapping
    public ResponseEntity<List<CestaListagemDto>> listar(){
        List<CestaListagemDto> listar = cestaMapper.toDto(cestaService.listar());
        if(listar.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listar);
    }

    @PostMapping
    public ResponseEntity<CestaListagemDto> criar(@Valid @RequestBody CestaCriacaoDto cesta){
        Cesta salvar = cestaService.criar(cestaMapper.toEntity(cesta));
        URI uri = URI.create("/cestas/" + salvar.getId());
        return ResponseEntity.created(uri).body(cestaMapper.toDto(salvar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CestaListagemDto> porId(@PathVariable Integer id){
        return ResponseEntity.ok(cestaMapper.toDto(cestaService.porId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CestaListagemDto> atualizar(@PathVariable Integer id, @RequestBody CestaAtualizacaoDto nova){
        Cesta cesta = cestaMapper.toEntity(nova);
        CestaListagemDto dto = cestaMapper.toDto(cestaService.atualizar(id, cesta));
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        cestaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CestaListagemDto> atualizarTipoCestaId(@PathVariable Integer id, @RequestBody CestaAtualizacaoTipoCestaIdDto tipoCestaId){
        cestaService.atualizar(id, cestaMapper.toEntity(tipoCestaId));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/quantidade-cestas")
    public ResponseEntity<CestaCountDto> qtdCestaPorMes(){
        return ResponseEntity.ok(cestaMapper.toDto(cestaService.qtdMesAtual()));
    }

    @GetMapping("/quantidade/items/cesta-principal")
    public ResponseEntity<CestaCountDto> qtdItemsCestaPrincipal(){
        return ResponseEntity.ok(cestaMapper.toDto(cestaService.qtdItemsCestaPrincipal()));
    }


}
