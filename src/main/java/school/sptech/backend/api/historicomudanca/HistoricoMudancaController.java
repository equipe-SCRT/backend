package school.sptech.backend.api.historicomudanca;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import school.sptech.backend.domain.historicomudanca.HistoricoMudanca;
import school.sptech.backend.service.historicomudanca.HistoricoMudancaService;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaAtualizacaoDto;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaCriacaoDto;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaListagemDto;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/historico-mudancas")
public class HistoricoMudancaController {

    private final HistoricoMudancaService service;
    private final HistoricoMudancaMapper mapper;

    

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid HistoricoMudancaCriacaoDto historico){

        service.criar(mapper.toEntity(historico));
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<HistoricoMudancaListagemDto>> listar(){

        List<HistoricoMudanca> dtos = service.listar();
        return ResponseEntity.ok(mapper.toDto(dtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoMudancaListagemDto> porId(@PathVariable int id) {

        HistoricoMudanca dto = service.porId(id);
        return ResponseEntity.ok().body(mapper.toDto(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoricoMudancaListagemDto> atualizar(@RequestBody HistoricoMudancaAtualizacaoDto historicoAtualizacao, @PathVariable Long id) {


        HistoricoMudanca dto = service.atualizar(mapper.toEntity(historicoAtualizacao), id);

        return ResponseEntity.ok().body(mapper.toDto(dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
