package school.sptech.backend.api.tipocesta;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.service.tipocesta.dto.TipoCestaCriacaoDto;
import school.sptech.backend.service.tipocesta.dto.TipoCestaListagemDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tipos-cestas")
public class TipoCestaController {

    @PostMapping
    public ResponseEntity<TipoCestaListagemDto> cadastrar(@RequestBody TipoCestaCriacaoDto tipoCesta){
        return null;
    }

    @GetMapping
    public ResponseEntity<TipoCestaListagemDto> listar(){
        return null;
    }
}
