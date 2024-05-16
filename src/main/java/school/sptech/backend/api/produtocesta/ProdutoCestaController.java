package school.sptech.backend.api.produtocesta;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.domain.produtocesta.entity.ProdutoCesta;
import school.sptech.backend.domain.produtocesta.mapper.ProdutoCestaMapper;
import school.sptech.backend.service.produtocesta.ProdutoCestaService;
import school.sptech.backend.service.produtocesta.dto.ProdutoCestaEntityDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produto-cesta")
public class ProdutoCestaController {

    private ProdutoCestaService service;

    @PostMapping
    public ResponseEntity<ProdutoCestaEntityDto> produtoCestaResponseEntity(@Valid @RequestBody ProdutoCesta produtoCesta){
        ProdutoCesta adicionado = service.adicionar(produtoCesta);
        ProdutoCestaEntityDto dto = ProdutoCestaMapper.toDto(adicionado);
        return ResponseEntity.status(201).body(dto);

    }
    @GetMapping
    public ResponseEntity<List<ProdutoCestaEntityDto>> getMap(){
        List<ProdutoCesta> produtoCestas = service.get();
        List<ProdutoCestaEntityDto> dto = ProdutoCestaMapper.toDto();
        if (dto.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(dto);    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoCestaEntityDto> getMap(@PathVariable int id){
        ProdutoCesta produtoCesta = service.get(id);
        ProdutoCestaEntityDto dto = ProdutoCestaMapper.toDto(produtoCesta);
        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMap(@PathVariable int id){
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoCestaEntityDto> updateMap(@Valid @RequestBody ProdutoCesta produtoCesta, @PathVariable int id){
        return null;
    }
}
