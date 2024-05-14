package school.sptech.backend.api.produtocesta;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.domain.produtocesta.entity.ProdutoCesta;

@RestController
@RequestMapping("/produto-cesta")
public class ProdutoCestaController {
    @PostMapping
    public ResponseEntity<ProdutoCesta> produtoCestaResponseEntity(@Valid @RequestBody ProdutoCesta produtoCesta){
        return null;
    }
    @GetMapping
    public ResponseEntity<ProdutoCesta> getMap(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoCesta> getMap(@PathVariable int id){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMap(@PathVariable int id){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoCesta> updateMap(@Valid @RequestBody ProdutoCesta produtoCesta, @PathVariable int id){
        return null;
    }
}
