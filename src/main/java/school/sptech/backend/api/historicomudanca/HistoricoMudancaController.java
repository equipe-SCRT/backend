package school.sptech.backend.api.historicomudanca;

import java.net.URI;
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
    public ResponseEntity<HistoricoMudancaListagemDto> criar(@RequestBody HistoricoMudancaCriacaoDto historico){

        System.out.println(historico.toString());
        HistoricoMudanca historicoMudanca = mapper.toEntity(historico);
        System.out.println(historicoMudanca.toString());
        HistoricoMudanca resposta = service.criar(historicoMudanca, historico.getFkUsuario());
        System.out.println(resposta);
        HistoricoMudancaListagemDto listagemDto = mapper.toDto(resposta);
        System.out.println(listagemDto);

        URI uri = URI.create("/historico-mudancas/" + historicoMudanca.getIdHistoricoMudanca());
        return ResponseEntity.created(uri).body(listagemDto);
    }

    @GetMapping
    public ResponseEntity<List<HistoricoMudancaListagemDto>> listar(){

        List<HistoricoMudanca> dtos = service.listar();
        List<HistoricoMudancaListagemDto> listagemDtos = mapper.toDto(dtos);
        return ResponseEntity.ok(listagemDtos);
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
