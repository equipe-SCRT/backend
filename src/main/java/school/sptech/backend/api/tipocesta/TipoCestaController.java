package school.sptech.backend.api.tipocesta;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import school.sptech.backend.api.BaseController;
import school.sptech.backend.domain.tipocesta.TipoCesta;
import school.sptech.backend.service.tipocesta.TipoCestaService;
import school.sptech.backend.service.tipocesta.dto.TipoCestaAtualizacaoDto;
import school.sptech.backend.service.tipocesta.dto.TipoCestaCriacaoDto;
import school.sptech.backend.service.tipocesta.dto.TipoCestaListagemDto;
import school.sptech.backend.service.tipocesta.dto.TipoCestaMapper;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tipos-cestas")
public class TipoCestaController implements BaseController<TipoCestaCriacaoDto, TipoCestaAtualizacaoDto, TipoCestaListagemDto, Integer> {
    private final TipoCestaService tipoCestaService;
    private final TipoCestaMapper tipoCestaMapper;
    @PostMapping
    public ResponseEntity<TipoCestaListagemDto> criar(@Valid @RequestBody TipoCestaCriacaoDto tipoCesta){
        TipoCestaListagemDto salvo = tipoCestaMapper.toDto(tipoCestaService.criar(tipoCestaMapper.toEntity(tipoCesta)));
        URI uri = URI.create("/tipos-cestas/" + salvo.getId());
        return ResponseEntity.created(uri).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<TipoCestaListagemDto>> listar(){
        List<TipoCesta> listar = tipoCestaService.listar();
        if(listar.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(tipoCestaMapper.toDto(listar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCestaListagemDto> porId(@PathVariable Integer id){
        return ResponseEntity.ok(tipoCestaMapper.toDto(tipoCestaService.porId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCestaListagemDto> atualizar(@PathVariable Integer id, @RequestBody TipoCestaAtualizacaoDto novo){
        TipoCestaListagemDto dto = tipoCestaMapper.toDto(tipoCestaService.atualizar(id, tipoCestaMapper.toEntity(novo)));
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        tipoCestaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}