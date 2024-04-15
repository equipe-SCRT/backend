package school.sptech.backendscrt.api.historicomudanca;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.domain.historicomudanca.HistoricoMudanca;
import school.sptech.backendscrt.service.historicomudanca.HistoricoMudancaService;
import school.sptech.backendscrt.service.historicomudanca.dto.HistoricoMudancaAtualizacaoDto;
import school.sptech.backendscrt.service.historicomudanca.dto.HistoricoMudancaCriacaoDto;
import school.sptech.backendscrt.service.historicomudanca.dto.HistoricoMudancaListagemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/historicos-mudancas")
@Tag(name = "HistoricoMudanca")
public class HistoricoMudancaController {

    @Autowired
    private HistoricoMudancaService historicoMudancaService;

    List<HistoricoMudancaListagemDto> historicosMudanca = new ArrayList<>();

    @PostMapping
    public ResponseEntity<HistoricoMudanca> adicionar(@RequestBody @Valid HistoricoMudancaCriacaoDto historicoMudancaCriacaoDto) {
        if (Objects.isNull(historicoMudancaCriacaoDto)) {
            return ResponseEntity.status(400).build();
        }
        this.historicoMudancaService.adicionar(historicoMudancaCriacaoDto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<HistoricoMudancaListagemDto>> listar() {
        this.historicoMudancaService.listar();
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoricoMudancaListagemDto> atualizar(@PathVariable int id, @RequestBody HistoricoMudancaAtualizacaoDto historicoMudancaAtualizacaoDto) {
        if (isInList(id)) {
            this.historicoMudancaService.atualizar(id, historicoMudancaAtualizacaoDto);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        if (isInList(id)) {
            this.historicoMudancaService.deletar(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int id){
        return id > 0 && id < historicosMudanca.size();
    }
}
