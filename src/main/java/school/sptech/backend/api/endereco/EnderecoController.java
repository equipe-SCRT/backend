package school.sptech.backend.api.endereco;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.domain.endereco.Endereco;
import school.sptech.backend.service.endereco.EnderecoService;
import school.sptech.backend.service.endereco.dto.EnderecoAtualizacaoDto;
import school.sptech.backend.service.endereco.dto.EnderecoCriacaoDto;
import school.sptech.backend.service.endereco.dto.EnderecoListagemDto;
import school.sptech.backend.service.endereco.dto.EnderecoMapper;

import java.net.URI;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoService service;
    private final EnderecoMapper mapper;

    @PostMapping
    public ResponseEntity<EnderecoListagemDto> salvar(@RequestBody EnderecoCriacaoDto dto)  {
        Endereco criar = service.criar(mapper.toEntity(dto));
        URI uri = URI.create("/enderecos/"+criar.getId());
        return ResponseEntity.created(uri).body(mapper.toDto(criar));

    }

    @GetMapping
    public ResponseEntity<List<EnderecoListagemDto>> listar() {
        List<EnderecoListagemDto> dto = mapper.toDto(service.listar());
        if(dto.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoListagemDto> porId(@PathVariable Integer id){
        return ResponseEntity.ok(mapper.toDto(service.porId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoListagemDto> atualizar(@PathVariable Integer id, @RequestBody EnderecoAtualizacaoDto endereco){
        service.atualizar(id, mapper.toEntity(endereco));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnderecoListagemDto> deletar(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
