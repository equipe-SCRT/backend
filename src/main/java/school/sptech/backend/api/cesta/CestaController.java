package school.sptech.backend.api.cesta;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.service.cesta.CestaService;
import school.sptech.backend.service.cesta.dto.CestaCriacaoDto;
import school.sptech.backend.service.cesta.dto.CestaListagemDto;
import school.sptech.backend.service.cesta.dto.CestaMapper;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cestas")
public class CestaController {
    private final CestaService cestaService;
    private final CestaMapper cestaMapper;

    @GetMapping
    public ResponseEntity<List<CestaListagemDto>> listar(){
        List<CestaListagemDto> listar = cestaMapper.cestaToDto(cestaService.listar());
        if(listar.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listar);
    }

    @PostMapping
    public ResponseEntity<CestaListagemDto> cadastrar(@RequestBody CestaCriacaoDto cesta){
        Cesta salvar = cestaService.salvar(cestaMapper.dtoToCesta(cesta));
        URI uri = URI.create("/cestas/" + salvar.getId());
        return ResponseEntity.created(uri).body(cestaMapper.cestaToDto(salvar));
    }
}
