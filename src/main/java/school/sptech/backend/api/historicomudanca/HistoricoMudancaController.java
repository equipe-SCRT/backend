package school.sptech.backend.api.historicomudanca;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import school.sptech.backend.api.BaseController;
import school.sptech.backend.domain.historicomudanca.HistoricoMudanca;
import school.sptech.backend.service.historicomudanca.HistoricoMudancaService;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaAtualizacaoDto;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaCriacaoDto;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaListagemDto;
import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaMapper;


@RequiredArgsConstructor
@RestController
@RequestMapping("/historico-mudancas")
public class HistoricoMudancaController implements BaseController<HistoricoMudancaCriacaoDto,HistoricoMudancaAtualizacaoDto, HistoricoMudancaListagemDto, Integer> {

    private final HistoricoMudancaService service;
    private final HistoricoMudancaMapper mapper;

    @PostMapping
    public ResponseEntity<HistoricoMudancaListagemDto> criar(@RequestBody @Valid HistoricoMudancaCriacaoDto historico){

        System.out.println(historico.toString());
        HistoricoMudanca historicoMudanca = mapper.toEntity(historico);
        System.out.println(historicoMudanca.toString());
        HistoricoMudanca resposta = service.criar(historicoMudanca);
        System.out.println(resposta);
        HistoricoMudancaListagemDto listagemDto = mapper.toDto(resposta);
        System.out.println(listagemDto);

        URI uri = URI.create("/historico-mudancas/" + historicoMudanca.getId());
        return ResponseEntity.created(uri).body(listagemDto);
    }

    @GetMapping
    public ResponseEntity<List<HistoricoMudancaListagemDto>> listar(){

        List<HistoricoMudanca> dtos = service.listar();
        List<HistoricoMudancaListagemDto> listagemDtos = mapper.toDto(dtos);
        if (listagemDtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listagemDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoMudancaListagemDto> porId(@PathVariable Integer id) {

        HistoricoMudanca dto = service.porId(id);
        return ResponseEntity.ok().body(mapper.toDto(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoricoMudancaListagemDto> atualizar(@PathVariable Integer id, @Valid @RequestBody HistoricoMudancaAtualizacaoDto historicoAtualizacao) {

        HistoricoMudanca entity = mapper.toEntity(historicoAtualizacao);
        HistoricoMudanca dto = service.atualizar(id, entity);

        return ResponseEntity.ok().body(mapper.toDto(dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
